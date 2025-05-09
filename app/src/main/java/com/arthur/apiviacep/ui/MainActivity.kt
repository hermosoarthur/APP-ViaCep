package com.arthur.apiviacep.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthur.apiviacep.api.ViaCepService
import com.arthur.apiviacep.database.AppDatabase
import com.arthur.apiviacep.databinding.ActivityMainBinding
import com.arthur.apiviacep.model.Endereco
import com.arthur.apiviacep.repository.EnderecoRepository
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EnderecoAdapter
    private lateinit var repository: EnderecoRepository
    private var enderecoAtual: Endereco? = null
    private var editando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)
        repository = EnderecoRepository(db.enderecoDao())

        adapter = EnderecoAdapter(
            onDelete = { deletarEndereco(it) },
            onEdit = { preencherParaEdicao(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        repository.enderecos.observe(this) {
            adapter.submitList(it)
        }

        binding.btnBuscar.setOnClickListener {
            val cep = binding.editCep.text.toString().trim()
            if (cep.length == 8 && cep.all { it.isDigit() }) {
                buscarEndereco(cep)
            } else {
                Toast.makeText(this, "Digite um CEP válido (8 números)", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAtualizar.setOnClickListener {
            if (!editando) {
                habilitarCampos(true)
                binding.btnAtualizar.text = "Salvar"
                editando = true
            } else {
                enderecoAtual?.let {
                    val atualizado = it.copy(
                        logradouro = binding.editLogradouro.text.toString(),
                        bairro = binding.editBairro.text.toString(),
                        localidade = binding.editCidade.text.toString(),
                        uf = binding.editUf.text.toString()
                    )
                    salvarEndereco(atualizado)
                    preencherCampos(atualizado)
                    Toast.makeText(this, "Endereço atualizado.", Toast.LENGTH_SHORT).show()
                }
                habilitarCampos(false)
                binding.btnAtualizar.text = "Atualizar"
                editando = false
            }
        }
    }

    private fun buscarEndereco(cep: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ViaCepService::class.java)
        service.buscarCep(cep).enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                if (response.isSuccessful) {
                    val endereco = response.body()
                    if (endereco != null && endereco.cep != null) {
                        enderecoAtual = endereco
                        preencherCampos(endereco)
                        salvarEndereco(endereco)
                    } else {
                        Toast.makeText(applicationContext, "CEP não encontrado.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Erro ao buscar o CEP.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun preencherCampos(endereco: Endereco) {
        binding.editCep.setText(endereco.cep ?: "")
        binding.editLogradouro.setText(endereco.logradouro ?: "")
        binding.editBairro.setText(endereco.bairro ?: "")
        binding.editCidade.setText(endereco.localidade ?: "")
        binding.editUf.setText(endereco.uf ?: "")
    }

    private fun habilitarCampos(habilitar: Boolean) {
        binding.editLogradouro.isEnabled = habilitar
        binding.editBairro.isEnabled = habilitar
        binding.editCidade.isEnabled = habilitar
        binding.editUf.isEnabled = habilitar
    }

    private fun salvarEndereco(endereco: Endereco) {
        lifecycleScope.launch {
            repository.salvar(endereco)
        }
    }

    private fun deletarEndereco(endereco: Endereco) {
        lifecycleScope.launch {
            repository.deletar(endereco)
        }
    }

    private fun preencherParaEdicao(endereco: Endereco) {
        enderecoAtual = endereco
        preencherCampos(endereco)
        habilitarCampos(true)
        binding.btnAtualizar.text = "Salvar"
        editando = true
    }
}

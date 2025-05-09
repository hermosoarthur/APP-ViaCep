package com.arthur.apiviacep.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arthur.apiviacep.databinding.ItemEnderecoBinding
import com.arthur.apiviacep.model.Endereco

class EnderecoAdapter(
    val onDelete: (Endereco) -> Unit,
    val onEdit: (Endereco) -> Unit
) : ListAdapter<Endereco, EnderecoAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemEnderecoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(endereco: Endereco) {
            binding.txtInfo.text = "${endereco.logradouro}, ${endereco.bairro} - ${endereco.localidade}/${endereco.uf}"
            binding.txtCep.text = "CEP: ${endereco.cep}"

            binding.btnExcluir.setOnClickListener {
                onDelete(endereco)
            }

            binding.btnEditar.setOnClickListener {
                onEdit(endereco)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Endereco>() {
        override fun areItemsTheSame(old: Endereco, new: Endereco) = old.id == new.id
        override fun areContentsTheSame(old: Endereco, new: Endereco) = old == new
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEnderecoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

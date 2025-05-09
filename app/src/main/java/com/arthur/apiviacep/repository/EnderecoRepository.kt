package com.arthur.apiviacep.repository

import androidx.lifecycle.LiveData
import com.arthur.apiviacep.dao.EnderecoDao
import com.arthur.apiviacep.model.Endereco

class EnderecoRepository(private val dao: EnderecoDao) {

    val enderecos: LiveData<List<Endereco>> = dao.listarTodos()

    suspend fun salvar(endereco: Endereco) {
        val existente = dao.buscarPorCep(endereco.cep ?: "")
        if (existente != null) {
            // Mantém o ID existente para evitar conflito
            val atualizado = endereco.copy(id = existente.id)
            dao.atualizar(atualizado)
        } else {
            dao.inserir(endereco)
        }
    }

    suspend fun atualizar(endereco: Endereco) {
        dao.atualizar(endereco)
    }

    suspend fun deletar(endereco: Endereco) {
        dao.deletar(endereco)
    }
}

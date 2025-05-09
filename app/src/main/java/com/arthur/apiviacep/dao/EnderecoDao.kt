package com.arthur.apiviacep.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arthur.apiviacep.model.Endereco

@Dao
interface EnderecoDao {

    @Query("SELECT * FROM Endereco WHERE cep = :cep LIMIT 1")
    suspend fun buscarPorCep(cep: String): Endereco?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserir(endereco: Endereco)

    @Delete
    suspend fun deletar(endereco: Endereco)

    @Update
    suspend fun atualizar(endereco: Endereco)

    @Query("SELECT * FROM Endereco ORDER BY id DESC")
    fun listarTodos(): LiveData<List<Endereco>>
}

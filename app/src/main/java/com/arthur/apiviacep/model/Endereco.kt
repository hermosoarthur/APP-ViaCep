package com.arthur.apiviacep.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Endereco(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)

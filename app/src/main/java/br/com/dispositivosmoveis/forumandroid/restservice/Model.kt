package com.moveis.forum.restservice

import java.io.Serializable
import java.util.*


data class Categoria(var id: Int? = null, var nome: String?): Serializable{

    override fun toString(): String = nome.toString()
}

data class Topico(var id: Int? = null, var titulo: String?, var autor: String?, var descricao: String?,
                  var categoria: String,var created_at: Date? = null, var updated_at: Date? = null) : Serializable{

    override fun toString(): String = titulo.toString() + "Autor: " +autor.toString()
}

data class Comentario(var id: Int? = null, var descricao :String?, var categoria: Int?, var topico: Int?) : Serializable
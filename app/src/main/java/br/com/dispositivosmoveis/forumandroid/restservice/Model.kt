package com.moveis.forum.restservice

import java.io.Serializable
import java.util.*


data class Categoria(var id: Int? = null, var nome: String?): Serializable{

    override fun toString(): String = nome.toString().toUpperCase()
}

data class Topico(var id: Int? = null, var titulo: String?, var autor: String?, var descricao: String?,
                  var categoria: String,var created_at: Date? = null, var updated_at: Date? = null) : Serializable{

    override fun toString(): String = titulo.toString().toUpperCase() + " - Autor: " +autor.toString()
}

data class Comentario(var id: Int? = null,  var topico:String, var comentario :String, var autor: String,var created_at: Date? = null, var updated_at: Date? = null) : Serializable{

    override fun toString(): String = comentario.toString().toUpperCase() + " Autor:" + autor
}
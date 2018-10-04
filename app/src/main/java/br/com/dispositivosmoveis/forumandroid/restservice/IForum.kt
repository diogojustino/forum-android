package br.com.dispositivosmoveis.forumandroid.restservice

import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.Comentario
import com.moveis.forum.restservice.Topico
import retrofit2.http.*
import retrofit2.Call

interface IForum {

    //CATEGORIA

    @GET("/categoria/")
    fun getCategorias(): Call<List<Categoria>>

    @POST("/categoria/")
    fun insertCategoria(@Body categoria: Categoria): Call<Categoria>

    @DELETE("/categoria/{id}/")
    fun removeCategoria(@Path("id") id: Int): Call<Categoria>

    @PUT("/categoria/{id}/")
    fun updateCategoria(@Path("id") id: Int, @Body categoria: Categoria): Call<Categoria>

    @GET("/categoria/{id}/topicos/")
    fun getTopicos(@Path("id") id: Int): Call<List<Topico>>


    //TÓPICOS

    @GET("/topico/")
    fun getTopicos(): Call<List<Topico>>

    @POST("/topico/")
    fun insertTopico(@Body topico: Topico): Call<Topico>

    @DELETE("/topico/{id}/")
    fun removeTopico(@Path("id") id: Int): Call<Topico>

    @PUT("/topico/{id}")
    fun updateTopico(@Path("id") id: Int, @Body topico: Topico): Call<Topico>

    @GET("/topico/{id}/comentarios")
    fun getComentarios(@Path("id") id: Int): Call<Categoria>


    //COMENTÁRIOS


    @GET("/comentario/")
    fun getComentario(): Call<List<Comentario>>

    @POST("/comentario")
    fun insertComentario(@Body comentario: Comentario): Call<Comentario>

    @PUT("/comentario/{id}/")
    fun updateComentario(@Path("id") id: Int, @Body comentario: Comentario): Call<Comentario>

    @DELETE("/comentario/{id}/")
    fun removeComentario(@Path("id") id: Int): Call<Comentario>

}
package br.com.dispositivosmoveis.forumandroid.restservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    val URL = "http://mapforum.in4.com.br"
    private val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun modelsService() = retrofit.create(IForum::class.java)
}
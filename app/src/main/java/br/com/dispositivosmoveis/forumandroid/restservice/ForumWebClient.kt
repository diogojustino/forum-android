package com.moveis.forum.restservice

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForumWebClient {

    val TAG = "WEBCLIENT"


    fun getTopicos(categoria: Categoria, iCallbackResponse: ICallbackResponse<List<Topico>>? = null) {
        val service = RetrofitInitializer().modelsService()
        val call = service.getTopicos(categoria.id!!)
        call.enqueue(object : Callback<List<Topico>?> {
            override fun onResponse(call: Call<List<Topico>?>?, response: Response<List<Topico>?>?) {
                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val topicos: List<Topico> = it
                        iCallbackResponse.success(topicos)
                    }
                }
                Log.i(TAG, "[INFO] getCategorias sucessfull.")
            }

            override fun onFailure(call: Call<List<Topico>?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] getCategorias error.")
            }
        })
    }

    fun insertTopico(topico: Topico, iCallbackResponse: ICallbackResponse<List<Topico>>? = null) {
        val service = RetrofitInitializer().modelsService()
        val call = service.insertTopico(topico)
        call.enqueue(object : Callback<Topico?> {
            override fun OnResponse(call: Call<Topico?>?, responde: Response<Topico?>?) {
                if (iCallbackResponse != null) {
                    response?.body().let {
                        val topico: Topico = it
                        iCallbackResponse.success(topico)
                    }
                }
                Log.i(TAG, "[INFO] insertTopico sucesfull.")
            }

            override fun onFailure(call: Call<Topico?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] insertTopico error.")
            }
        })
    }

    fun updateTopico(id: Int, topico: Topico, iCallbackResponse: ICallbackResponse<Topico>? = null) {
        val service = RetrofitInitializer().modelsService()
        val call = service.updateTopico(id, topico)
        call.enqueue(object : Callback<Topico?> {
            override fun onResponse(call: Call<Topico?>?, response: Response<Topico?>?) {
                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val topico: Topico = it
                        iCallbackResponse.success(topico)
                    }
                }
                Log.i(TAG, "[INFO] updateTopico sucessfull.")
            }
            override fun onFailure(call: Call<Topico?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] updateTopico error.")
            }
        })
    }

    fun removeTopico(id: Int, iCallbackResponse: ICallbackResponse<Topico>? = null) {
        val service = RetrofitInitializer().modelsService()
        val call = service.removeTopico(id)
        call.enqueue(object : Callback<Topico?> {
            override fun onResponse(call: Call<Topico?>?, response: Response<Topico?>?) {
                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val topico: Topico = it
                        iCallbackResponse.success(topico)
                    }
                }
                Log.i(TAG, "[INFO] removeTopico sucessfull.")
            }

            override fun onFailure(call: Call<Topico?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] removeTopico error.")
            }
        })
    }


}
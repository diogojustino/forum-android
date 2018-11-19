package com.moveis.forum.restservice

import android.content.ContentValues.TAG
import android.util.Log
import br.com.dispositivosmoveis.forumandroid.restservice.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForumWebClient {

    val TAG = "WEBCLIENT"


    fun getCategorias(iCallbackResponse: ICallbackResponse<List<Categoria>>? = null) {
        val service = RetrofitInitializer().modelsService()


        val call = service.getCategorias()

        call.enqueue(object : Callback<List<Categoria>?> {

            override fun onResponse(call: Call<List<Categoria>?>?, response: Response<List<Categoria>?>?) {


                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val categorias: List<Categoria> = it
                        iCallbackResponse.success(categorias)
                    }
                }

                Log.i(TAG, "[INFO] getCategorias sucessfull.")
            }

            override fun onFailure(call: Call<List<Categoria>?>?, t: Throwable?) {
                Log.e(TAG, t.toString())
                Log.e(TAG, "[ERROR] getCategorias error.")
            }
        })
    }

    fun insertCategoria(categoria: Categoria, iCallbackResponse: ICallbackResponse<Categoria>? = null) {
        val service = RetrofitInitializer().modelsService()

        val call = service.insertCategoria(categoria)
        call.enqueue(object : Callback<Categoria?> {
            override fun onResponse(call: Call<Categoria?>?, response: Response<Categoria?>?) {
                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val categoria: Categoria = it
                        iCallbackResponse.success(categoria)
                    }
                }
                Log.i(TAG, "[INFO] insertCategoria sucessfull.")
            }

            override fun onFailure(call: Call<Categoria?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] insertCategoria error.")
            }
        })
    }

    fun insertTopico(topico: Topico, iCallbackResponse: ICallbackResponse<Topico>? = null) {

        val service = RetrofitInitializer().modelsService()
        val call = service.insertTopico(topico)

        call.enqueue(object : Callback<Topico?> {
            override fun onResponse(call: Call<Topico?>?, response: Response<Topico?>?) {
                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val topico: Topico = it
                        iCallbackResponse.success(topico)
                        Log.i(TAG, "[INFO] insertTopico sucesfull.")
                    }
                }

            }

            override fun onFailure(call: Call<Topico?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] insertTopico error.")
            }
        })
    }

    fun updateCategoria(id: Int, categoria: Categoria, iCallbackResponse: ICallbackResponse<Categoria>? = null) {
        val service = RetrofitInitializer().modelsService()

        val call = service.updateCategoria(id, categoria)
        call.enqueue(object : Callback<Categoria?> {
            override fun onResponse(call: Call<Categoria?>?, response: Response<Categoria?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val categoria: Categoria = it
                        iCallbackResponse.success(categoria)
                    }
                }
                Log.i(TAG, "[INFO] updateCategoria sucessfull.")
            }

            override fun onFailure(call: Call<Categoria?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] updateCategoria error.")
            }
        })
    }

    fun removeCategoria(id: Int, iCallbackResponse: ICallbackResponse<Categoria>? = null) {
        val service = RetrofitInitializer().modelsService()

        val call = service.removeCategoria(id)
        call.enqueue(object : Callback<Categoria?> {
            override fun onResponse(call: Call<Categoria?>?, response: Response<Categoria?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val categoria: Categoria = it
                        iCallbackResponse.success(categoria)
                    }
                }
                Log.i(TAG, "[INFO] removeCategoria sucessfull.")
            }

            override fun onFailure(call: Call<Categoria?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] removeCategoria error.")
            }
        })
    }

    fun getTopicos(categoria: Categoria, iCallbackResponse: ICallbackResponse<List<Topico>>? = null) {
        val service = RetrofitInitializer().modelsService()

        val call = service.getTopicos(categoria.id!!)
        //Erro por aqui
        call.enqueue(object : Callback<List<Topico>?> {
            override fun onResponse(call: Call<List<Topico>?>?, response: Response<List<Topico>?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val topicos: List<Topico> = it
                        iCallbackResponse.success(topicos)

                    }
                }
                Log.i(TAG, "[INFO] getTopico sucessfull.")
            }

            override fun onFailure(call: Call<List<Topico>?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] getTopicos error.")
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

    fun getComentarios(categoria: Categoria, iCallbackResponse: ICallbackResponse<List<Comentario>>? = null) {

        val service = RetrofitInitializer().modelsService()
        val call = service.getComentario()
        call.enqueue(object : Callback<List<Comentario>?> {

            override fun onResponse(call: Call<List<Comentario>?>?, response: Response<List<Comentario>?>?) {


                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val comentarios: List<Comentario> = it
                        iCallbackResponse.success(comentarios)
                    }
                }

                Log.i(TAG, "[INFO] getTopicos sucessfull.")
            }

            override fun onFailure(call: Call<List<Comentario>?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] getTopicos error.")
            }
        })
    }


    fun insertComentario(comentario: Comentario, iCallbackResponse: ICallbackResponse<Comentario>? = null) {
        val service = RetrofitInitializer().modelsService()

        val call = service.insertComentario(comentario)
        call.enqueue(object : Callback<Comentario?> {
            override fun onResponse(call: Call<Comentario?>?, response: Response<Comentario?>?) {
                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val comentario: Comentario = it
                        iCallbackResponse.success(comentario)
                    }
                }
                Log.i(TAG, "[INFO] insertComentario sucessfull.")
            }

            override fun onFailure(call: Call<Comentario?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] insertComentario error.")
            }
        })
    }

    fun updateComentario(id: Int, comentario: Comentario, iCallbackResponse: ICallbackResponse<Comentario>? = null) {
        val service = RetrofitInitializer().modelsService()

        val call = service.updateComentario(id, comentario)
        call.enqueue(object : Callback<Comentario?> {
            override fun onResponse(call: Call<Comentario?>?, response: Response<Comentario?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val comentario: Comentario = it
                        iCallbackResponse.success(comentario)
                    }
                }
                Log.i(TAG, "[INFO] updateComentario sucessfull.")
            }

            override fun onFailure(call: Call<Comentario?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] updateComentario error.")
            }
        })
    }

    fun removerComentario(id: Int, iCallbackResponse: ICallbackResponse<Comentario>? = null) {

        val service = RetrofitInitializer().modelsService()
        val call = service.removerComentario(id)
        call.enqueue(object : Callback<Comentario?> {
            override fun onResponse(call: Call<Comentario?>?, response: Response<Comentario?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val comentario: Comentario = it
                        iCallbackResponse.success(comentario)
                    }
                }
                Log.i(TAG, "[INFO] removerComentario sucessfull.")
            }

            override fun onFailure(call: Call<Comentario?>?, t: Throwable?) {
                Log.e(TAG, "[ERROR] removerComentario error.")
            }
        })
    }
}
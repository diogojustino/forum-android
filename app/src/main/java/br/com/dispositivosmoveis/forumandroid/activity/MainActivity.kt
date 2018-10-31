package br.com.dispositivosmoveis.forumandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico

class MainActivity : AppCompatActivity() {

    private var categorias: List<Categoria>? = null
    private var topicos: List<Topico>? = null
    private var comentarios: List<Comentario>? = null
    private val TAG = "MAIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart(){
        super.onStart();


        ForumWebClient().getCategorias(object : ICallbackResponse<List<Categoria>>{
            override fun success(categorias: List<Categoria>) {
                this@MainActivity.categorias = categorias

                for (cat in categorias!!){
                    alerta(cat.toString())
                    Log.i(TAG + "Categoria", cat.toString())
                }
            }
        })



    }

    fun alerta(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}

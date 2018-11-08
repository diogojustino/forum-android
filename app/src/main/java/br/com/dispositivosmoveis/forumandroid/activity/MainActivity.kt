package br.com.dispositivosmoveis.forumandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.dispositivosmoveis.forumandroid.R
//import br.com.dispositivosmoveis.forumandroid.adapter.ListCategoriaAdapter
import com.moveis.forum.restservice.*

class MainActivity : AppCompatActivity() {

    private var categorias: List<Categoria>? = null
    private var topicos: List<Topico>? = null
    private var comentarios: List<Comentario>? = null
    private val TAG = "MAIN"
    //var listCategoriaAdapter: ListCategoriaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
    }

    //IRÁ PEGAR OS DADOS NA API E APENAS EXIBIR RÁPIDAMENTE COMO SE FOSSE UM POP-UP
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

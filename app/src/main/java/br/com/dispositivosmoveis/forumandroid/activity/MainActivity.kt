package br.com.dispositivosmoveis.forumandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import br.com.dispositivosmoveis.forumandroid.R
//import br.com.dispositivosmoveis.forumandroid.adapter.ListCategoriaAdapter
import com.moveis.forum.restservice.*
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private val cate: List<Categoria>? = null

    private var categorias: List<Categoria>? = null
    private var topicos: List<Topico>? = null
    private var comentarios: List<Comentario>? = null
    private val TAG = "MAIN"
    //var listCategoriaAdapter: ListCategoriaAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

        ForumWebClient().getCategorias(object : ICallbackResponse<List<Categoria>> {
            override fun success(categorias: List<Categoria>) {
                this@MainActivity.categorias = categorias

                for (cat in categorias!!) {
                    //alerta(cat.nome!!)
                    Log.i(TAG + "Categoria", cat.toString())

                    listView = findViewById(R.id.listView)


                    val categorias = arrayOf(cat.nome)

                    listView.adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, categorias)


                }


            }
        })


    }

    //IRÁ PEGAR OS DADOS NA API
//    override fun onStart(){
//        super.onStart();
//
//        ForumWebClient().getCategorias(object : ICallbackResponse<List<Categoria>>{
//            override fun success(categorias: List<Categoria>) {
//                this@MainActivity.categorias = categorias
//
//                for (cat in categorias!!){
//                    alerta(cat.nome!!)
//                    Log.i(TAG + "Categoria", cat.toString())
//
//                    listView = findViewById(R.id.listView)
//                }
//            }
//        })
//    }


//    //EXIBE RÁPIDAMENTE NA TELA COMO SE FOSSE UM POP-UP
//    fun alerta(mensagem: String){
//        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
//    }
}

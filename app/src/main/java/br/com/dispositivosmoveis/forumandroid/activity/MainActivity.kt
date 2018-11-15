package br.com.dispositivosmoveis.forumandroid.activity


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.*

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val cate: List<Categoria>? = null

    private var categorias: List<Categoria>? = null
    private var topicos: List<Topico>? = null
    private var comentarios: List<Comentario>? = null
    private val TAG = "MAIN"
    //var listCategoriaAdapter: ListCategoriaAdapter? = null

    private lateinit var btnAddCategoria: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
        btnAddCategoria = findViewById(R.id.btn_add_categoria) as Button
        btnAddCategoria.setOnClickListener({
            val intent:Intent = Intent(this@MainActivity.getBaseContext(), NovaCategoriaActivity::class.java)
            startActivity(intent)
        })
    }

    //IRÁ PEGAR OS DADOS NA API
    override fun onStart(){
        super.onStart();

        ForumWebClient().getCategorias(object : ICallbackResponse<List<Categoria>> {
            override fun success(categorias: List<Categoria>) {
                this@MainActivity.categorias = categorias
                val listaCategorias= arrayListOf<String>()
                for (categoria in categorias!!) {
                    //alerta(cat.nome!!)
                    Log.i(TAG + "Categoria", categoria.toString())

                    listView = findViewById(R.id.listView)


                    listaCategorias.add(categoria.nome!!)



                }
                listView.adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, listaCategorias)


            }
        })
    }


    //EXIBE RÁPIDAMENTE NA TELA COMO SE FOSSE UM POP-UP
    fun alerta(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}

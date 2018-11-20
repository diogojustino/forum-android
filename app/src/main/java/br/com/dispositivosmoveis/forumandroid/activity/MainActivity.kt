package br.com.dispositivosmoveis.forumandroid.activity


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.*

import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.*

class MainActivity : ModeloActivity() {

    private lateinit var listView: ListView
    private lateinit var btnAddCategoria: Button

    private lateinit var btnRemoveCategoria: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAddCategoria = findViewById<Button>(R.id.btn_add_categoria)
        listView = findViewById(R.id.listView) as ListView

        //btnRemoveCategoria = findViewById(R.id.removeButton)

        //CRIAR NOVA CATEGORIA
        btnAddCategoria.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity.getBaseContext(), NovaCategoriaActivity::class.java)
            startActivity(intent)
        }

        //EXIBIR A LISTA DE TOPICOS DE UMA CATEGORIA
        listView.setOnItemClickListener { parent, view, position, id ->
            var categoria: Categoria = parent.adapter.getItem(position) as Categoria
            val intent: Intent = Intent(this@MainActivity.baseContext, TopicosActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("categoria", categoria)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //REMOVE UMA CATEGORIA AO SEGURAR ELA
        listView.setOnItemLongClickListener { parent, view, position, id ->

            var categoria: Categoria = parent.adapter.getItem(position) as Categoria
            var categoriaID = categoria.id
            ForumWebClient().removeCategoria(categoriaID!!)
            this@MainActivity.recreate()
            alerta("Categoria ${categoria.nome!!.toUpperCase()} removida com sucesso")

            true

        }


    }

    //IRÁ PEGAR OS DADOS NA API
    override fun onStart() {
        super.onStart()

        ForumWebClient().getCategorias(object : ICallbackResponse<List<Categoria>> {
            override fun success(categorias: List<Categoria>) {

                val listaCategorias = arrayListOf<Categoria>()
                for (categoria in categorias!!) {

                    listaCategorias.add(categoria!!)

                }
                listView.adapter = ArrayAdapter<Categoria>(this@MainActivity, android.R.layout.simple_list_item_1, listaCategorias) as ListAdapter?
            }
        })
    }
}

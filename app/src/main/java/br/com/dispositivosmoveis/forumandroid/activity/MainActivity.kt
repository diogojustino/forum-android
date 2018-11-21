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
    private lateinit var buttonNovaCategoria: ImageButton

    private lateinit var btnRemoveCategoria: ImageButton
    private lateinit var buttonEditarCategoria: ImageButton
    private var categoria: Categoria? = null
    private val KEY = "categoria"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonNovaCategoria = findViewById<ImageButton>(R.id.button_nova_categoria)
        buttonEditarCategoria = findViewById<ImageButton>(R.id.button_editar)
        listView = findViewById(R.id.listView) as ListView

        btnRemoveCategoria = findViewById(R.id.removeButton)

        //CRIAR NOVA CATEGORIA
        buttonNovaCategoria.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity.getBaseContext(), NovaCategoriaActivity::class.java)
            startActivity(intent)
        }
        buttonEditarCategoria.setOnClickListener {
           if(categoria != null){
               var bundle = Bundle()
               val intent: Intent = Intent(this@MainActivity.getBaseContext(), NovaCategoriaActivity::class.java)

               bundle.putSerializable(KEY, categoria )
               intent.putExtras(bundle)
               startActivity(intent)
           }else{

               alerta("ESCOLHA A CATEGORIA!")
           }


        }

        //EXIBIR A LISTA DE TOPICOS DE UMA CATEGORIA
        listView.setOnItemClickListener { parent, view, position, id ->
            var categoria: Categoria = parent.adapter.getItem(position) as Categoria
            val intent: Intent = Intent(this@MainActivity.baseContext, TopicosActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable(KEY, categoria)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //SELECIONA O UMA CATEGORIA PARA REMOVER OU EDITAR
        listView.setOnItemLongClickListener { parent, view, position, id ->

            categoria = parent.adapter.getItem(position) as Categoria

            alerta("Categoria ${categoria!!.nome!!.toUpperCase()} selecionada")

            true

        }

        //BOTÃO DE REMOVER UMA CATEGORIA COM O ID SELECIONADO
        btnRemoveCategoria.setOnClickListener {



            if (categoria != null) {

                ForumWebClient().removeCategoria(categoria!!.id!!)
                this@MainActivity.recreate()
                alerta("Categoria removida com sucesso")
                categoria = null
            } else {
                alerta("ESCOLHA A CATEGORIA!")
            }
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

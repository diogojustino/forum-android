package br.com.dispositivosmoveis.forumandroid.activity

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.*

import br.com.dispositivosmoveis.forumandroid.R

import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico

class TopicosActivity : ModeloActivity() {

    private lateinit var categoriaEscolhida: Categoria
    private var topicoEscolhido: Topico? = null
    private lateinit var buttonRemover: ImageButton
    private lateinit var buttonNovoTopico: ImageButton
    private lateinit var buttonEditar: ImageButton
    private lateinit var listTopicos: ListView

    private lateinit var btnRemoveTopico: ImageButton

    private val KEY_TOPICO = "topico"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topicos)

        categoriaEscolhida = intent.extras.getSerializable("categoria") as Categoria
        buttonNovoTopico = findViewById(R.id.button_novo_topico)
        buttonEditar = findViewById(R.id.button_editar)
        buttonRemover = findViewById(R.id.button_remover)
        listTopicos = findViewById(R.id.list_topico) as ListView

        btnRemoveTopico = findViewById(R.id.button_remover)

        //EXIBIR A LISTA DE TOPICOS DE UMA CATEGORIA
        listTopicos.setOnItemClickListener { parent, view, position, id ->
            var topico: Topico = parent.adapter.getItem(position) as Topico
            val intent: Intent = Intent(this@TopicosActivity.getBaseContext(), ComentariosActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable(KEY_TOPICO, topico)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //CRIA UM NOVO TOPICO
        buttonNovoTopico.setOnClickListener {
            val intent: Intent = Intent(this@TopicosActivity.getBaseContext(), NovoTopicoActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("categoria", categoriaEscolhida)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        buttonEditar.setOnClickListener {
            if(topicoEscolhido != null){
                var bundle = Bundle()
                val intent: Intent = Intent(this@TopicosActivity.getBaseContext(), NovoTopicoActivity::class.java)

                bundle.putSerializable(KEY_TOPICO, topicoEscolhido )
                intent.putExtras(bundle)
                startActivity(intent)
            }else{

                alerta("ESCOLHA O TOPICO!")
            }


        }


        //SELECIONA O ID DE UM TOPICO PARA REMOVER OU EDITAR
        listTopicos.setOnItemLongClickListener { parent, view, position, id ->

            topicoEscolhido = parent.adapter.getItem(position) as Topico


            alerta("Topico ${topicoEscolhido!!.titulo!!.toUpperCase()} selecionado")

            true

        }


        //BOTÃO DE REMOVER UM TÓPICO COM O ID SELECIONADO
        btnRemoveTopico.setOnClickListener {



            if (topicoEscolhido != null) {

                ForumWebClient().removeTopico(topicoEscolhido!!.id!!)
                this@TopicosActivity.recreate()
                alerta("Topico removido com sucesso")
            } else {
                alerta("SELECIONE UM TÓPICO!")
            }
        }
    }

    override fun onStart() {
        super.onStart()

        ForumWebClient().getTopicos(categoriaEscolhida, object : ICallbackResponse<List<Topico>> {
            override fun success(topicos: List<Topico>) {
                val listaTopicos = arrayListOf<Topico>()
                for (topico in topicos!!) {

                    listaTopicos.add(topico!!)

                }
                listTopicos.adapter = ArrayAdapter<Topico>(this@TopicosActivity, android.R.layout.simple_list_item_1, listaTopicos) as ListAdapter?

            }
        })
    }
}

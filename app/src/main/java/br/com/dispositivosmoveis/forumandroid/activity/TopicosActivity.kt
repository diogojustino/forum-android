package br.com.dispositivosmoveis.forumandroid.activity

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter

import android.widget.Button

import android.widget.ListAdapter
import android.widget.ListView
import br.com.dispositivosmoveis.forumandroid.R

import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico

class TopicosActivity : ModeloActivity() {

    private lateinit var categoriaEscolhida: Categoria
    private lateinit var buttonAdicionarTopico: Button
    private lateinit var listTopicos: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topicos)

        categoriaEscolhida = intent.extras.getSerializable("categoria") as Categoria
        buttonAdicionarTopico = findViewById(R.id.button_adicionar_topico)
        listTopicos = findViewById(R.id.list_topico) as ListView

        //EXIBIR A LISTA DE TOPICOS DE UMA CATEGORIA
        listTopicos.setOnItemClickListener { parent, view, position, id ->
            var topico: Topico = parent.adapter.getItem(position) as Topico
            val intent: Intent = Intent(this@TopicosActivity.getBaseContext(), ComentariosActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("topico", topico)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //CRIA UM NOVO TOPICO
        buttonAdicionarTopico.setOnClickListener {
            val intent: Intent = Intent(this@TopicosActivity.getBaseContext(), NovoTopicoActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("categoria", categoriaEscolhida)
            intent.putExtras(bundle)
            startActivity(intent)
        }


        //REMOVE UM TOPICO AO SEGURAR ELE
        listTopicos.setOnItemLongClickListener { parent, view, position, id ->

            var topico: Topico = parent.adapter.getItem(position) as Topico
            var topicoID = topico.id
            ForumWebClient().removeTopico(topicoID!!)
            this@TopicosActivity.recreate()
            alerta("Topico ${topico.titulo!!.toUpperCase()} foi removido com sucesso")

            true

        }
    }

    override fun onStart() {
        super.onStart()

        ForumWebClient().getTopicos(categoriaEscolhida, object: ICallbackResponse<List<Topico>> {
            override fun success(topicos: List<Topico>) {
                val listaTopicos= arrayListOf<Topico>()
                for (topico in topicos!!) {

                    listaTopicos.add(topico!!)

                }
                listTopicos.adapter = ArrayAdapter<Topico>(this@TopicosActivity, android.R.layout.simple_list_item_1, listaTopicos) as ListAdapter?

            }
        })
    }
}

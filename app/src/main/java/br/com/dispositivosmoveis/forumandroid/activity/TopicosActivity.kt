package br.com.dispositivosmoveis.forumandroid.activity

import android.content.Intent
import android.os.Bundle
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
        listTopicos = findViewById(R.id.list_topico)

        buttonAdicionarTopico.setOnClickListener {
            val intent: Intent = Intent(this@TopicosActivity.getBaseContext(), NovoTopicoActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("categoria", categoriaEscolhida)
            intent.putExtras(bundle)
            startActivity(intent)
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

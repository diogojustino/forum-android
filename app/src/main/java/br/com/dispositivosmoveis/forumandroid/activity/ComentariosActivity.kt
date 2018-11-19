package br.com.dispositivosmoveis.forumandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Comentario
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico

class ComentariosActivity : ModeloActivity() {

    private lateinit var topicoEscolhido: Topico
    private lateinit var listComentario: ListView
    private lateinit var btnAddComentario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_comentario)

        topicoEscolhido = intent.extras.getSerializable("topico") as Topico
        btnAddComentario = findViewById(R.id.btnNovoComentario)
        listComentario = findViewById(R.id.list_comentarios)

        btnAddComentario.setOnClickListener {
            val intent: Intent = Intent(this@ComentariosActivity.getBaseContext(), NovoComentarioActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("topico", topicoEscolhido)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        ForumWebClient().getComentarios(topicoEscolhido, object: ICallbackResponse<List<Comentario>>{
            override fun success(comentarios: List<Comentario>) {
                val listaComentarios = arrayListOf<Comentario>()
                for (comentario in comentarios!!){
                    listaComentarios.add(comentario!!)
                }
                listComentario.adapter = ArrayAdapter<Comentario>(this@ComentariosActivity, android.R.layout.simple_list_item_1, listaComentarios)
            }
        })
    }
}

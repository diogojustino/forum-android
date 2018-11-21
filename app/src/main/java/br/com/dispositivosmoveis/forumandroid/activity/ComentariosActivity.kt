package br.com.dispositivosmoveis.forumandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Comentario
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico

class ComentariosActivity : ModeloActivity() {

    private lateinit var topicoEscolhido: Topico
    private lateinit var listComentario: ListView
    private lateinit var buttonNovoComentario: Button
    private lateinit var textAutor: TextView
    private lateinit var textDescricao: TextView
    private lateinit var textTitulo: TextView

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_comentarios)

        topicoEscolhido = intent.extras.getSerializable("topico") as Topico
        buttonNovoComentario = findViewById(R.id.button_novo_comentario)
        listComentario = findViewById(R.id.list_comentarios)
        textAutor = findViewById(R.id.text_autor)
        textDescricao = findViewById(R.id.text_descricao)
        textTitulo = findViewById(R.id.text_titulo)


        textAutor.setText("Autor: " + topicoEscolhido.autor.toString())
        textDescricao.setText("Descricao: " + topicoEscolhido.descricao.toString())
        textTitulo.setText("Tituto: " + topicoEscolhido.titulo.toString())

        buttonNovoComentario.setOnClickListener {
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

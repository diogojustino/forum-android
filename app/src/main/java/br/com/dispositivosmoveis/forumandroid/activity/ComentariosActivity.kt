package br.com.dispositivosmoveis.forumandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Comentario
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico

class ComentariosActivity : ModeloActivity() {

    private lateinit var topicoEscolhido: Topico
    private lateinit var listComentario: ListView

    private lateinit var textAutor: TextView
    private lateinit var textDescricao: TextView
    private lateinit var textTitulo: TextView
    private lateinit var buttonRemover: ImageButton
    private lateinit var buttonNovoCometario: ImageButton
    private lateinit var buttonEditar: ImageButton


    private var comentario: Comentario? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_comentarios)


        buttonNovoCometario = findViewById(R.id.button_novo_comentario)
        buttonEditar = findViewById(R.id.button_editar)
        buttonRemover = findViewById(R.id.button_remover)

        topicoEscolhido = intent.extras.getSerializable("topico") as Topico
        listComentario = findViewById(R.id.list_comentarios)
        textAutor = findViewById(R.id.text_autor)
        textDescricao = findViewById(R.id.text_descricao)
        textTitulo = findViewById(R.id.text_titulo)




        textAutor.setText("Autor: " + topicoEscolhido.autor.toString())
        textDescricao.setText("Descricao: " + topicoEscolhido.descricao.toString())
        textTitulo.setText("Tituto: " + topicoEscolhido.titulo.toString())

        buttonNovoCometario.setOnClickListener {
            val intent: Intent = Intent(this@ComentariosActivity.getBaseContext(), NovoComentarioActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("topico", topicoEscolhido)
            intent.putExtras(bundle)
            startActivity(intent)
        }


        //SELECIONA O ID DE UM COMENTÁRIO PARA REMOVER OU EDITAR
        listComentario.setOnItemLongClickListener { parent, view, position, id ->

            comentario = parent.adapter.getItem(position) as Comentario



            alerta("Comentario ${comentario!!.autor!!.toUpperCase()} selecionada")

            true

        }


        //BOTÃO DE REMOVER UM COMENTÁRIO COM O ID SELECIONADO
        buttonRemover.setOnClickListener {
            if (comentario != null) {

                ForumWebClient().removerComentario(comentario!!.id!!)
                this@ComentariosActivity.recreate()
                alerta("Comentário removido com sucesso")
            } else {
                alerta("ESCOLHA O COMENTÁRIO!")
            }
        }

        buttonEditar.setOnClickListener {
            if(topicoEscolhido != null){
                var bundle = Bundle()
                val intent: Intent = Intent(this@ComentariosActivity.getBaseContext(), NovoComentarioActivity::class.java)

                bundle.putSerializable("comentario", comentario )
                intent.putExtras(bundle)
                startActivity(intent)
            }else{

                alerta("ESCOLHA O TOPICO!")
            }


        }

    }

    override fun onStart() {
        super.onStart()

        ForumWebClient().getComentarios(topicoEscolhido, object : ICallbackResponse<List<Comentario>> {
            override fun success(comentarios: List<Comentario>) {
                val listaComentarios = arrayListOf<Comentario>()
                for (comentario in comentarios!!) {
                    listaComentarios.add(comentario!!)
                }
                listComentario.adapter = ArrayAdapter<Comentario>(this@ComentariosActivity, android.R.layout.simple_list_item_1, listaComentarios)
            }
        })
    }
}

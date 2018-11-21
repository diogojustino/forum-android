package br.com.dispositivosmoveis.forumandroid.activity


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Comentario
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico
import java.util.Date

class NovoComentarioActivity : ModeloActivity() {

    private lateinit var topicoEscolhido: Topico
    private lateinit var buttonSalvar: Button
    private lateinit var editAutor: EditText
    private lateinit var editComentario: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_comentario)

        topicoEscolhido = intent.extras.getSerializable("topico") as Topico
        buttonSalvar = findViewById(R.id.button_salvar)
        editAutor = findViewById(R.id.edit_autor)
        editComentario = findViewById(R.id.edit_comentario)



        buttonSalvar.setOnClickListener {
            var autor = editAutor.text.toString()
            var descricao = editComentario.text.toString()

            if (autor.isEmpty()) {
                alerta("Autor Vazio!!")
                return@setOnClickListener
            }
            if (descricao.isEmpty()) {
                alerta("Comentario Vazio!!")
                return@setOnClickListener
            }


            var comentario: Comentario = Comentario(null, topicoEscolhido.id.toString(), descricao, autor,null, null)
            ForumWebClient().insertComentario(comentario, object : ICallbackResponse<Comentario> {

                override fun success(instance: Comentario) {
                    alerta("topico ${comentario.autor!!.toUpperCase()} criado com sucesso")
                    finish()
                }
            })
        }
    }


}

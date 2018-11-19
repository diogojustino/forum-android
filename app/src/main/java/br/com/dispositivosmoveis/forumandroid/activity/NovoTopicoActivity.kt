package br.com.dispositivosmoveis.forumandroid.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico
import java.util.*

class NovoTopicoActivity : ModeloActivity() {
    private lateinit var categoriaEscolhida: Categoria
    private lateinit var btnSalvar: Button
    private lateinit var editAutor: EditText
    private lateinit var editTitulo: EditText
    private lateinit var editDescricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_topico)

        categoriaEscolhida = intent.extras.getSerializable("categoria") as Categoria


        btnSalvar = findViewById(R.id.btnSalvar) as Button
        editAutor = findViewById(R.id.edit_autor) as EditText
        editTitulo = findViewById(R.id.edit_titulo) as EditText
        editDescricao = findViewById(R.id.edit_descricao) as EditText

        btnSalvar.setOnClickListener {
            var autor = editAutor.text.toString()
            var titulo = editTitulo.text.toString()
            var descricao = editDescricao.text.toString()
            if(autor.isEmpty() || titulo.isEmpty() || descricao.isEmpty()){
                alerta("Falha!!")
                return@setOnClickListener
            }

            var topico: Topico = Topico(null, titulo, autor, descricao, categoriaEscolhida, Date(), Date())
            ForumWebClient().insertTopico(topico, object : ICallbackResponse<Topico> {

                override fun success(instance: Topico){
                    alerta("Sucesso")
                }
            })

            finish()
        }

    }
}

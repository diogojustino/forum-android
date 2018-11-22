package br.com.dispositivosmoveis.forumandroid.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import com.moveis.forum.restservice.Topico
import java.util.Date

class NovoTopicoActivity : ModeloActivity() {
    private lateinit var categoriaEscolhida: Categoria
    private lateinit var btnSalvar: Button
    private lateinit var editAutor: EditText
    private lateinit var editTitulo: EditText
    private lateinit var editDescricao: EditText
    private var topicoEscolhida: Topico? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_topico)

        var bundle = intent.extras

        topicoEscolhida = bundle.getSerializable("topico") as? Topico
        btnSalvar = findViewById(R.id.btnSalvar) as Button
        editAutor = findViewById(R.id.edit_autor) as EditText
        editTitulo = findViewById(R.id.edit_titulo) as EditText
        editDescricao = findViewById(R.id.edit_descricao) as EditText
        if (topicoEscolhida == null) {
            categoriaEscolhida = intent.extras.getSerializable("categoria") as Categoria
        }else{
            editAutor.setText(topicoEscolhida!!.autor)
            editTitulo.setText(topicoEscolhida!!.titulo)
            editDescricao.setText(topicoEscolhida!!.descricao)

        }

        btnSalvar.setOnClickListener {
            var autor = editAutor.text.toString()
            var titulo = editTitulo.text.toString()
            var descricao = editDescricao.text.toString()
            if(autor.isEmpty()){
                alerta("Autor Vazio!!")
                return@setOnClickListener
            }
            if(titulo.isEmpty()){
                alerta("Tituto Vazio!!")
                return@setOnClickListener
            }
            if( descricao.isEmpty()){
                alerta("Descricao Vazio!!")
                return@setOnClickListener
            }

            if(topicoEscolhida == null) {
                var topico: Topico = Topico(null, titulo, autor, descricao, categoriaEscolhida.id.toString())
                Log.i("WEBCLIENT", topico.autor)
                ForumWebClient().insertTopico(topico, object : ICallbackResponse<Topico> {

                    override fun success(instance: Topico) {
                        alerta("topico ${topico.titulo!!.toUpperCase()} criado com sucesso")
                        finish()
                    }
                })

            }else{
                topicoEscolhida!!.autor = autor
                topicoEscolhida!!.titulo = titulo
                topicoEscolhida!!.descricao = descricao

                ForumWebClient().updateTopico(topicoEscolhida!!.id!!, topicoEscolhida!!, object: ICallbackResponse<Topico>{
                    override fun success(instance: Topico) {
                        alerta("topico ${topicoEscolhida!!.titulo!!.toUpperCase()} atualizado com sucesso")
                        finish()
                    }

                })
            }
        }

    }
}

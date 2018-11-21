package br.com.dispositivosmoveis.forumandroid.activity

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import br.com.dispositivosmoveis.forumandroid.R
import br.com.dispositivosmoveis.forumandroid.restservice.RetrofitInitializer
import com.moveis.forum.restservice.Categoria
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.ICallbackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovaCategoriaActivity : ModeloActivity(){

    private lateinit var btnSalvar: Button
    private lateinit var editCategoria: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_categoria)

        btnSalvar = findViewById(R.id.btnSalvar) as Button
        editCategoria = findViewById(R.id.edit_categoria) as EditText

        btnSalvar.setOnClickListener {
            var nomeCategoria = editCategoria.text.toString()
            if(nomeCategoria.isEmpty()){
                alerta("Falha!!")
                return@setOnClickListener
            }

            var categoria: Categoria = Categoria(null, nomeCategoria)
            ForumWebClient().insertCategoria(categoria, object : ICallbackResponse<Categoria> {

                override fun success(instance: Categoria){
                    alerta("Categoria ${categoria.nome!!.toUpperCase()} criada com sucesso")
                    finish()
                }
            })

        }
    }


}

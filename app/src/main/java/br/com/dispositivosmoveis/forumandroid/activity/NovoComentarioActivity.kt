package br.com.dispositivosmoveis.forumandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import br.com.dispositivosmoveis.forumandroid.R
import com.moveis.forum.restservice.ForumWebClient
import com.moveis.forum.restservice.Topico
//
//class NovoComentarioActivity : AppCompatActivity() {
//
//    private lateinit var topicoEscolhido: Topico
//    private lateinit var listComentario: ListView
//    private lateinit var btnAddComentario: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_novo_comentario)
//
//        topicoEscolhido = intent.extras.getSerializable("topico") as Topico
//        btnAddComentario = findViewById(R.id.btnSalvarComentario)
//        listComentario = findViewById(R.id.list_comentarios)
//
//
//
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        ForumWebClient().getComentarios()
//    }
//}

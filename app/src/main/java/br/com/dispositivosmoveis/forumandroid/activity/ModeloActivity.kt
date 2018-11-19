package br.com.dispositivosmoveis.forumandroid.activity

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class ModeloActivity : AppCompatActivity() {
    //EXIBE RÁPIDAMENTE NA TELA COMO SE FOSSE UM POP-UP
    fun alerta(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

}
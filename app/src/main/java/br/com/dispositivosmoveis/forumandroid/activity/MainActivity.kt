package br.com.dispositivosmoveis.forumandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dispositivosmoveis.forumandroid.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart(){
        super.onStart();
    }

    fun alerta(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG)
    }
}

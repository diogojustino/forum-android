//package br.com.dispositivosmoveis.forumandroid.adapter
//
//import android.content.Intent
//import android.support.v7.app.AppCompatActivity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.ImageButton
//import android.widget.TextView
//import br.com.dispositivosmoveis.forumandroid.R
//import com.moveis.forum.restservice.Categoria
//import com.moveis.forum.restservice.ForumWebClient
//import com.moveis.forum.restservice.ICallbackResponse
//
//class ListCategoriaAdapter(private val activity: AppCompatActivity): BaseAdapter(){
//
//    val TAG = "ListCategoriaAdapter"
//
//    var list: List<Categoria>? = null
//    private val mInflator: LayoutInflater
//
//
//    // INICIALIZANDO O mInflator e chamando o método de atualizar a categoria
//    init {
//        this.mInflator = LayoutInflater.from(activity)
//        dataUpdate()
//    }
//
//    fun dataUpdate(){
//        // Pega o progress bar
//        var swipeRefresh = activity.swipeRefresh
//        swipeRefresh.isRefreshing = true
//
//        ForumWebClient().getCategorias(object: ICallbackResponse<List<Categoria>> {
//            override fun success(instance: List<Categoria>) {
//                list = instance
//
//                // Ajusta alterações
//                this@ListCategoriaAdapter.notifyDataSetChanged()
//
//                // Seta invisible no progress bar
//                swipeRefresh.isRefreshing = false
//            }
//        })
//    }
//
//    override fun getCount(): Int {
//        if(list == null){
//            return 0
//        }
//        return list!!.size
//    }
//
//    override fun getItem(i: Int): Categoria? {
//        if(list == null){
//            return null
//        }
//        return list!![i]
//    }
//
//    override fun getItemId(i: Int): Long {
//        return i.toLong()
//    }
//
//    override fun getView(i: Int, convertView: View?, parent: ViewGroup): View? {
//        val view: View?
//        if (convertView == null) {
//            view = this.mInflator.inflate(R.layout.categoria_row, parent, false)
//        } else {
//            view = convertView
//        }
//
//        // Get Categoria
//        val categoria = getItem(i)
//
//        // Label
//        var label = view?.findViewById<TextView>(R.id.titulo)
//        label?.text = categoria?.nome
//
//        // Edit button
//        var editButton = view?.findViewById<ImageButton>(R.id.editButton)
//        editButton?.setOnClickListener {
//            var intent = Intent(activity, AddCategoriaActivity::class.java)
//            intent.putExtra("instance", categoria)
//            activity.startActivity(intent)
//        }
//
//        // Remove button
//        var removeButton = view?.findViewById<ImageButton>(R.id.removeButton)
//        removeButton?.setOnClickListener {
//
//            // Usando o Snackbar para confirmar a remoção
//            Snackbar.make(view!!, "Deseja remover esta categoria?", Snackbar.LENGTH_LONG).setAction("Sim", {
//                ForumWebClient().removeCategoria(categoria?.id!!)
//                activity.toast("Categoria removida com sucesso!")
//                dataUpdate()
//            }).show()
//
//
//        }
//
//        // Ação de clique
//        view?.setOnClickListener({
//            this.activity.toast("Clicou em ${categoria?.nome}")
//        })
//
//        return view
//    }
//
//
//
//}
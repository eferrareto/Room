package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.database.App
import com.example.room.database.Users
import com.example.room.database.UsersDao

class MainActivity : AppCompatActivity() {

    private lateinit var etName : EditText
    private lateinit var etAge : EditText
    private lateinit var btnInsert : Button
    private lateinit var btnDelete : Button
    private lateinit var btnUpdate : Button
    private lateinit var rc : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.txtName)
        etAge = findViewById(R.id.txtAge)
        btnInsert = findViewById(R.id.button)
        btnDelete = findViewById(R.id.buttonDelete)
        btnUpdate = findViewById(R.id.buttonUpdate)
        rc = findViewById(R.id.recycler)

        val list = mutableListOf<Users>()

        rc.adapter = ListAdapter(list)
        rc.layoutManager = LinearLayoutManager(this)



        btnInsert.setOnClickListener{
            val updateList = rc.adapter
            updateList?.notifyDataSetChanged()
            Thread{
                
                val app = application as App
                val dao = app.db.userDao()

                //Função inserir DAO
                dao.insertUsers(Users(etName.text.toString(), etAge.text.toString().toInt()))

                //Função pegar DAO
                val response = dao.getAllUsers()
                list.addAll(response)

            }.start()

        }


        btnDelete.setOnClickListener {
            Thread {

                val app = application as App
                val dao = app.db.userDao()

                //Função deletar dados da tabela
                dao.deleteAll()

            }.start()
            val updateList = rc.adapter
            updateList?.notifyDataSetChanged()
        }

        btnUpdate.setOnClickListener{
            Thread{
                val app = application as App
                val dao = app.db.userDao()


            }.start()

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }



    inner class ListAdapter(val listNames : MutableList<Users>): RecyclerView.Adapter<ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val inflater = layoutInflater.inflate(R.layout.activity_list, parent, false)
            return ListViewHolder(inflater)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val state = listNames[position]
            holder.bind(state)
        }

        override fun getItemCount(): Int {
            return listNames.size
        }

    }
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(view : Users){

            val names : TextView = itemView.findViewById(R.id.txtNameList)
            val ages : TextView = itemView.findViewById(R.id.txtAgeList)
            names.text = view.nome
            ages.text = view.idade.toString()

        }

    }
}
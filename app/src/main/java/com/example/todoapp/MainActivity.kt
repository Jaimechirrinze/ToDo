package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_simplified.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapterToDo: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterToDo = ToDoAdapter(mutableListOf())

        tasksRecyclerView.adapter = adapterToDo
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)

        btnAddTask.setOnClickListener {

            val task = editTextTask.text.toString()
            if(task.isNotEmpty()){

                val task = ToDo(task)
                adapterToDo.addTask(task)
                editTextTask.text.clear()
            }else{

                Toast.makeText(applicationContext, "Preencha o campo vazio", LENGTH_LONG)
            }
        }

        deletTask.setOnClickListener {

            adapterToDo.deletTask()
        }
    }
}
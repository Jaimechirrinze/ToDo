package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

data class ToDoAdapter(

    //Criei uma lista Mutavel...
    //Quer dizer que o app apaga os dados quando eh destroido

    private val taskList : MutableList <ToDo>

) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(

            //Inflar o Layout das linhas da lista d itens

            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }


    //Riscar o texto se o checkBox estiver no true

    private fun strickTextOrNot(taskName : TextView, isCkecked : Boolean){


        if (isCkecked){

            //Falso logo que a task eh criada entao nao estara riscada
            taskName.paintFlags = taskName.paintFlags

        }else{

            //risca
            taskName.paintFlags = taskName.paintFlags and STRIKE_THRU_TEXT_FLAG
        }
    }

    //Essa funcao eh pra retornar o tamanho da lista
    override fun getItemCount(): Int {

        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //E qui onde eh feita a referencia dos textos que queremos que aparecam na tela
        //Pode ser na class MyItemViewHolder tambm...

        //isso eh novo mas gramei, eh maning easy

        val task = taskList[position]

        holder.itemView.apply {

            taskName.text = task.taskName
            taskCheckbox.isChecked = task.isCkecked

            strickTextOrNot(taskName, task.isCkecked)

            taskCheckbox.setOnCheckedChangeListener { _, isChecked ->

                strickTextOrNot(taskName, isChecked)
                task.isCkecked = isChecked
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //Esse metodo nem preciso explicar
    fun addTask(task : ToDo){

        taskList.add(task)
        notifyItemInserted(taskList.size -1)
    }

    //Esse me bateu no removeAll nunca usei mas faz sentido
    //
    fun deletTask(){

        taskList.removeAll {

            //aqui tambem nao percebi bem
            //So sei que o toDo isChecked ja vem no false mas ele apaga mesmo assim...
            //Foi uma gambiarra
            toDo -> toDo.isCkecked
        }

        notifyDataSetChanged()
    }
}

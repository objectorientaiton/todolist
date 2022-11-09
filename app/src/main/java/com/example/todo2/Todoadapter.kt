package com.example.todo2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo2.databinding.TodoItemBinding

class Todoadapter : RecyclerView.Adapter<Todoadapter.ViewHolder>() {
    private val todoList = ArrayList<TodoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Todoadapter.ViewHolder{
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Todoadapter.ViewHolder, position: Int) {
        val todoEntity = todoList[position]
        holder.setTodoListUI(todoEntity, position)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTodoList(todo: List<TodoEntity>?){
        if (todo != null){
            todoList.clear()
            todoList.addAll(todo)
            println(todoList)
        } else{
            todoList.clear()
        }
    }
    inner class ViewHolder(private val binding: TodoItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun setTodoListUI(todo: TodoEntity, position: Int){
            binding.todoDescription.text = todo.description
            binding.todoID.text = "$position"
            binding.todoDate.text = todo.date
        }
    }
}
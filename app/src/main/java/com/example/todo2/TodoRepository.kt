package com.example.todo2

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoRepository(application: Application) {
    private val todoDAO: TodoDao

    init{
        var db = TodoDataBase.getInstance(application)
        todoDAO = db!!.TodoDAO()
    }

    fun insert(todo: TodoEntity){
        todoDAO.insert(todo)
    }

    fun getAllByDate(date: String): LiveData<List<TodoEntity>?> {
        return todoDAO.getAllByDate(date)
    }

    fun getAll(): LiveData<List<TodoEntity>> {
        return todoDAO.getAll()
    }

    fun delete(todo: TodoEntity){
        GlobalScope.launch(Dispatchers.IO) {
            todoDAO.delete(todo)
        }
    }
}
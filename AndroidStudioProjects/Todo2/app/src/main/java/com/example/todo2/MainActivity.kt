package com.example.todo2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo2.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Todoadapter
    private val todoViewModel: TodoViewModel by viewModels()

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        val recyclerView = binding.todoRecycle
        setRecyclerView(recyclerView)

        todoViewModel.date.observe(this, androidx.lifecycle.Observer {
            Log.d("date", it.toString())
            todoViewModel.getAllByDate(it)
                .observe(this, { todoList ->
                    Log.d("todoList", todoList.toString())
                    if (todoList != null) {
                        adapter.setTodoList(todoList)
                        adapter.notifyDataSetChanged()
                    }
                })
        })
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val dateStr = "$year-${month + 1}-$dayOfMonth"
            todoViewModel.updateDate(dateStr)
        }
        binding.addButton.setOnClickListener {
            val description = binding.EditTodo.text.toString()
            GlobalScope.launch(Dispatchers.IO) {
                val date = todoViewModel.date.value!!
                todoViewModel.insert(TodoEntity(null, description, date))
            }
        }
    }

    private fun setRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = Todoadapter()
        recyclerView.adapter = adapter

        val dateOfToday = getTodayOfDate()
        todoViewModel.updateDate(dateOfToday)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTodayOfDate(): String {
        val dateOfTodayLong = binding.calendarView.date
        val day = SimpleDateFormat("yyyy-MM-dd")
        return day.format(dateOfTodayLong)
    }
}
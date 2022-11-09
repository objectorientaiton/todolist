package com.example.todo2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoDataBase::class], version = 1)
abstract class TodoDataBase : RoomDatabase(){
    abstract fun TodoDAO() : TodoDao

    companion object{
        var instance : TodoDataBase? = null
        fun getInstance(context: Context): TodoDataBase?{
            if(instance == null){
                synchronized(TodoDataBase::class){
                    instance = Room.databaseBuilder(context.applicationContext, TodoDataBase::class.java, "TodoDB.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}
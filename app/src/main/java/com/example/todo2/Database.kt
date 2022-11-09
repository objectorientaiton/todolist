package com.example.todo2

import androidx.room.Dao
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database

@Database(entities = [TodoEntity::class], version = 1)
@TypeConverters(TypeConverter ::class)
abstract class Database : RoomDatabase(){
    abstract fun Dao(): TodoDao
}
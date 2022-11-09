package com.example.todo2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(@PrimaryKey(autoGenerate = true) var id: Int?,
var description: String ="",
var date:String ="")
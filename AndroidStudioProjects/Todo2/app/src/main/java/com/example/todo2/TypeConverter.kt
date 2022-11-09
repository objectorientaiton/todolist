package com.example.todo2

import androidx.room.TypeConverter
import java.util.*

class TypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? = value?.let { Date(it) }
    @TypeConverter
    fun dateToTimeStamp(date : Date?) : Long? = date?.time
}
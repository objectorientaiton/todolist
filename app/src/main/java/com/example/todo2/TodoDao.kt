package com.example.todo2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    //데이터 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: TodoEntity)

    //날짜에 따른 데이터 가져오기
    @Query("SELECT * FROM todo WHERE date = :date")
    fun getAllByDate(date: String): LiveData<List<TodoEntity>?>

    //데이터 삭제
    @Delete
    fun delete(todo: TodoEntity)

    //모든 데이터 가져오기
    @Query("SELECT * From todo")
    fun getAll(): LiveData<List<TodoEntity>>
}
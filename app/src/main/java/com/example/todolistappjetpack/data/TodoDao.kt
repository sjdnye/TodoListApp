package com.example.todolistappjetpack.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id= :id")
    suspend fun getTodoById(id: Int): Todo?


}
package com.example.todolistappjetpack.repository

import com.example.todolistappjetpack.data.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TodoRepository{

    fun getAllTodo() : Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id: Int) : Todo?
}
package com.example.todolistappjetpack.ui.theme.todo_list.util

import com.example.todolistappjetpack.data.Todo

sealed class TodoListEvent{
    data class OnDeleteTodoClick(val todo: Todo): TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
    data class OnTodoCLick(val todo: Todo): TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
}

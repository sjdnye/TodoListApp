package com.example.todolistappjetpack.ui.theme.todo_list.screen.add_edit_todo

sealed class AddEditTodoEvent{
    data class OnTitleChange(val title:String): AddEditTodoEvent()
    data class OnDescriptionChange(val description:String): AddEditTodoEvent()
    object OnSaveTodoClick: AddEditTodoEvent()
}

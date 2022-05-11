package com.example.todolistappjetpack.ui.theme.todo_list.screen.todo_screen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistappjetpack.data.Todo
import com.example.todolistappjetpack.repository.TodoRepository
import com.example.todolistappjetpack.ui.theme.todo_list.util.Routes
import com.example.todolistappjetpack.ui.theme.todo_list.util.TodoListEvent
import com.example.todolistappjetpack.ui.theme.todo_list.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.getAllTodo()

    private var deletedTodo: Todo? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnTodoCLick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }

            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(event.todo.copy(
                        isDone = event.isDone
                    ))
                }

            }
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {

            _uiEvent.send(event)

        }
    }
}
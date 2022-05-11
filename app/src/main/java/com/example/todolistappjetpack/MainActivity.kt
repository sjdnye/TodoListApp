package com.example.todolistappjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistappjetpack.ui.theme.ToDoListAppJetpackTheme
import com.example.todolistappjetpack.ui.theme.todo_list.screen.todo_screen.TodoListScreen
import com.example.todolistappjetpack.ui.theme.todo_list.screen.add_edit_todo.AddEditTodoScreen
import com.example.todolistappjetpack.ui.theme.todo_list.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListAppJetpackTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.TODO_LIST) {
                    composable(route = Routes.TODO_LIST) {
                        TodoListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }

                    composable(
                        route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditTodoScreen(onPopBackState = {
                            navController.popBackStack()
                        })
                    }
                }

            }
        }
    }
}

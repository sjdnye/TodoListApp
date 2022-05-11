package com.example.todolistappjetpack.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistappjetpack.data.TodoDao
import com.example.todolistappjetpack.data.TodoDatabase
import com.example.todolistappjetpack.repository.TodoRepository
import com.example.todolistappjetpack.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        app: Application
    ): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao{
        return todoDatabase.dao
    }

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository{
        return TodoRepositoryImpl(dao)
    }
}
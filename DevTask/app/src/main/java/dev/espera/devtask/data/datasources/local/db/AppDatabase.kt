package dev.espera.devtask.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.espera.devtask.data.datasources.local.dao.TagDao
import dev.espera.devtask.data.datasources.local.dao.TaskDao
import dev.espera.devtask.data.datasources.local.dao.UserDao
import dev.espera.devtask.data.datasources.local.entities.Tag
import dev.espera.devtask.data.datasources.local.entities.Task
import dev.espera.devtask.data.datasources.local.entities.User

@Database(entities = [User::class, Task::class, Tag::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
    abstract fun tagDao(): TagDao
}
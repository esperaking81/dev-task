package dev.espera.devtask.data.datasources.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.espera.devtask.data.datasources.local.dao.TagDao
import dev.espera.devtask.data.datasources.local.dao.TaskDao
import dev.espera.devtask.data.datasources.local.dao.UserDao
import dev.espera.devtask.data.datasources.local.entities.TagEntity
import dev.espera.devtask.data.datasources.local.entities.TaskEntity
import dev.espera.devtask.data.datasources.local.entities.UserEntity
@Database(entities = [UserEntity::class, TaskEntity::class, TagEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
    abstract fun tagDao(): TagDao
}
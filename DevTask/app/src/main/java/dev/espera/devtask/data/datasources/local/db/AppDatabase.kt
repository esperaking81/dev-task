package dev.espera.devtask.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.espera.devtask.data.datasources.converters.DateTimeConverters
import dev.espera.devtask.data.datasources.local.dao.TagDao
import dev.espera.devtask.data.datasources.local.dao.TaskDao
import dev.espera.devtask.data.datasources.local.dao.UserDao
import dev.espera.devtask.data.datasources.local.entities.TagEntity
import dev.espera.devtask.data.datasources.local.entities.TaskAssigneeCrossRef
import dev.espera.devtask.data.datasources.local.entities.TaskEntity
import dev.espera.devtask.data.datasources.local.entities.TaskTagCrossRef
import dev.espera.devtask.data.datasources.local.entities.UserEntity
@Database(entities = [UserEntity::class, TaskEntity::class, TagEntity::class, TaskTagCrossRef::class, TaskAssigneeCrossRef::class], version = 1)
@TypeConverters(DateTimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
    abstract fun tagDao(): TagDao
}
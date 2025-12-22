package dev.espera.devtask.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.espera.devtask.data.datasources.local.entities.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert
    suspend fun insertAll(vararg tags: Tag)

    @Delete
    suspend fun delete(tag: Tag)

    @Query("SELECT * FROM tags")
    suspend fun getAll(): Flow<List<Tag>>
}
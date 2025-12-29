package dev.espera.devtask.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import dev.espera.devtask.data.datasources.local.entities.TagEntity

@Dao
interface TagDao {
    @Insert
    suspend fun insert(vararg tags: TagEntity)

    @Delete
    suspend fun delete(tag: TagEntity)

    @Query("DELETE FROM tags")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateTags(tags: List<TagEntity>){
        deleteAll()
        insert(*tags.toTypedArray())
    }


    @Query("SELECT * FROM tags")
    fun getAll(): Flow<List<TagEntity>>
}
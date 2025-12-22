package dev.espera.devtask.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tags",
    indices = [Index("name", unique = true)]
)
data class Tag(
    @PrimaryKey val id: String,
    val name: String,
    val color: String?,
    val createdAt: String,
)
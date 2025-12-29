package dev.espera.devtask.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.espera.devtask.domain.models.Tag

@Entity(
    tableName = "tags",
    indices = [Index("name", unique = true)]
)
data class TagEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val color: String,
    val createdAt: String,
)

fun TagEntity.toDomain(): Tag {
    return Tag(
        id = id,
        name = name,
        color = color,
    )
}
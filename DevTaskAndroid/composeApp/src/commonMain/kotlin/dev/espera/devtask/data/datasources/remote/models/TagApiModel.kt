package dev.espera.devtask.data.datasources.remote.models

import dev.espera.devtask.data.datasources.local.entities.TagEntity
import kotlinx.serialization.Serializable

@Serializable
data class TagApiModel(
    val color: String,
    val createdAt: String,
    val id: String,
    val name: String
)

fun TagApiModel.toEntity(): TagEntity {
    return TagEntity(
        id = id,
        name = name,
        color = color,
        createdAt = createdAt
    )
}
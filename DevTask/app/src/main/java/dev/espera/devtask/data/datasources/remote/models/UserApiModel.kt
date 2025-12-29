package dev.espera.devtask.data.datasources.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val id: String,
    val name: String,
    val email: String,
    val createdAt: String?,
    val updatedAt: String?
)
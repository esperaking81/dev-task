package dev.espera.devtask.data.datasources.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email: String,
    val password: String
)

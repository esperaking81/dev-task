package dev.espera.devtask.data.datasources.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val accessToken: String,
    val user: UserApiModel,
)

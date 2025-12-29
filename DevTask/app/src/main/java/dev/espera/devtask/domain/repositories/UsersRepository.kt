package dev.espera.devtask.domain.repositories

import dev.espera.devtask.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getCurrentUser(): Flow<User?>
    suspend fun logout(): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
}
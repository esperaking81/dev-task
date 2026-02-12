package dev.espera.devtask.domain.usecases

import dev.espera.devtask.domain.repositories.UsersRepository
import dev.espera.devtask.domain.models.User
import kotlinx.coroutines.flow.Flow

class GetLoggedInUserUseCase(
    private val usersRepository: UsersRepository,
) {
    fun invoke(): Flow<User?> = usersRepository.getCurrentUser()
}
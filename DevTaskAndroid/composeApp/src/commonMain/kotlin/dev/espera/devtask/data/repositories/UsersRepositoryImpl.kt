package dev.espera.devtask.data.repositories

import dev.espera.devtask.data.datasources.remote.models.LoginDto
import dev.espera.devtask.data.datasources.remote.sources.UsersLocalDataSource
import dev.espera.devtask.data.datasources.remote.sources.UsersRemoteDataSource
import dev.espera.devtask.domain.models.User
import dev.espera.devtask.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(
    val usersRemoteDataSource: UsersRemoteDataSource,
    val usersLocalDataSource: UsersLocalDataSource,
) : UsersRepository {
    override suspend fun logout(): Result<Unit> {
        usersLocalDataSource.logOut()
        return Result.success(Unit)
    }

    override fun getCurrentUser(): Flow<User?> {
        return usersLocalDataSource.getLoggedInUser()
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        try {
            val loginResponse = usersRemoteDataSource.login(
                LoginDto(email, password)
            )
            usersLocalDataSource.logIn(loginResponse)
            return Result.success(Unit)
        } catch (e: Exception) {
            println("login error: ${e.message} ${e.stackTrace}")
            return Result.failure(e)
        }
    }
}
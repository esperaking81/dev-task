package dev.espera.devtask.data.datasources.remote.sources

import dev.espera.devtask.data.model.AppState
import dev.espera.devtask.domain.models.User
import androidx.datastore.core.DataStore
import dev.espera.devtask.data.datasources.local.dao.UserDao
import dev.espera.devtask.data.datasources.local.entities.UserEntity
import dev.espera.devtask.data.datasources.local.entities.toDomain
import dev.espera.devtask.data.datasources.remote.models.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface UsersLocalDataSource {
    suspend fun logOut()
    val accessToken: Flow<String?>
    fun getLoggedInUser(): Flow<User?>
    suspend fun logIn(loginResponse: LoginResponse)
}


class UsersPreferencesDataSource(
    val userDao: UserDao,
    val dataStore: DataStore<AppState>,
) : UsersLocalDataSource {
    override val accessToken: Flow<String?>
        get() = dataStore.data.map { it.accessToken }

    override fun getLoggedInUser(): Flow<User?> = flow {
        val currentUserId = dataStore.data.first().userId
        if (currentUserId != null) {
            userDao.getById(currentUserId).collect {
                emit(it.toDomain())
            }
        }
    }

    override suspend fun logIn(loginResponse: LoginResponse) {
        val userName = loginResponse.user.name
        val userId = loginResponse.user.id
        val accessToken = loginResponse.accessToken

        userDao.insert(
            UserEntity(
                id = userId,
                createdAt = "",
                updatedAt = "",
                name = userName,
                email = loginResponse.user.email,
            )
        )

        val appState = AppState(
            userId = userId,
            userName = userName,
            accessToken = accessToken
        )

        dataStore.updateData { appState }
    }

    override suspend fun logOut() {
        dataStore.updateData { AppState(null, null, null) }
    }
}


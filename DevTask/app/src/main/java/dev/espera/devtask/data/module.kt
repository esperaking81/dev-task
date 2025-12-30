package dev.espera.devtask.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.room.Room
import dev.espera.devtask.data.datasources.local.db.AppDatabase
import dev.espera.devtask.data.datasources.remote.network.DevTaskApi
import dev.espera.devtask.data.datasources.remote.network.DevTaskApiImpl
import dev.espera.devtask.data.datasources.remote.sources.UsersLocalDataSource
import dev.espera.devtask.data.datasources.remote.sources.UsersNetworkDataSource
import dev.espera.devtask.data.datasources.remote.sources.UsersPreferencesDataSource
import dev.espera.devtask.data.datasources.remote.sources.UsersRemoteDataSource
import dev.espera.devtask.data.model.AppState
import dev.espera.devtask.data.model.AppStateSerializer
import dev.espera.devtask.data.repositories.TasksRepositoryImpl
import dev.espera.devtask.data.repositories.UsersRepositoryImpl
import dev.espera.devtask.domain.repositories.TasksRepository
import dev.espera.devtask.domain.repositories.UsersRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "devtask-db"
        ).build()
    }
}

private val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging)
        }
    }

    single<DevTaskApi> {
        DevTaskApiImpl(get())
    }
}

val Context.dataStore: DataStore<AppState> by dataStore(
    fileName = "app_state.json",
    serializer = AppStateSerializer,
)

val users = module {
    single<UsersRemoteDataSource> {
        UsersNetworkDataSource(get())
    }
    single<UsersLocalDataSource> {
        UsersPreferencesDataSource(
            get<AppDatabase>().userDao(), androidContext().dataStore,
        )
    }
    single<UsersRepository> {
        UsersRepositoryImpl(get(), get())
    }
}

val dataModule = module {
    includes(networkModule, databaseModule, users)
}
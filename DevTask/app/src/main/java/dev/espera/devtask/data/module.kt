package dev.espera.devtask.data

import androidx.room.Room
import dev.espera.devtask.data.datasources.local.db.AppDatabase
import dev.espera.devtask.data.datasources.remote.network.DevTaskApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

private val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "devtask-db"
        ).build()
    }

    single {
        get<AppDatabase>().userDao()
        get<AppDatabase>().taskDao()
        get<AppDatabase>().tagDao()
    }
}

private val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single {
        DevTaskApiImpl(get())
    }
}

val dataModule = module {
    includes(networkModule, databaseModule)
}
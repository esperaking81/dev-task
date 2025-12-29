package dev.espera.devtask.data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class AppState(
    val userId: String?,
    val userName: String?,
    val accessToken: String?,
)

object AppStateSerializer : Serializer<AppState> {
    override val defaultValue: AppState = AppState(
        userId = null,
        userName = null,
        accessToken = null
    )

    override suspend fun readFrom(input: InputStream): AppState = try {
        Json.decodeFromString<AppState>(
            input.readBytes().decodeToString()
        )
    } catch (serialization: SerializationException) {
        throw CorruptionException("Unable to read Settings", serialization) as Throwable
    }

    override suspend fun writeTo(
        t: AppState,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(AppState.serializer(), t).encodeToByteArray()
        )
    }

}
package dev.espera.devtask.common.failure

import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

sealed interface Failure {
    val message: String
    val statusCode: Int?

    data object NetworkFailure : Failure {
        override val message: String
            get() = "Network error"
        override val statusCode: Int?
            get() = null
    }

    data class ServerFailure(override val statusCode: Int) : Failure {
        override val message: String
            get() = "Server error"
    }

    data object UnknownFailure : Failure {
        override val message: String
            get() = "Unknown error"
        override val statusCode: Int?
            get() = null
    }
}

class ErrorHandler {
    fun parse(t: Throwable): Failure {
       return when (t) {
            is IOException -> Failure.NetworkFailure
            is ServerResponseException -> Failure.ServerFailure(t.response.status.value)
            else -> Failure.UnknownFailure
        }
    }
}
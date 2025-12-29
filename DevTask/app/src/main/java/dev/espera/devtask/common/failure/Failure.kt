package dev.espera.devtask.common.failure

sealed class Failure {
    data class NetworkFailure(val message: String, val statusCode: Int? = null) : Failure()
    data object CacheFailure : Failure()
    data object GenericFailure : Failure()
}
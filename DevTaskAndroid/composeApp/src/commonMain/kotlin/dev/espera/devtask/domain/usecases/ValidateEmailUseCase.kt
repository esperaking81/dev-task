package dev.espera.devtask.domain.usecases

import android.util.Patterns
import dev.espera.devtask.common.usecase.IBaseUseCase

class ValidateEmailUseCase : IBaseUseCase<String, String?> {
    override fun invoke(params: String): Result<String?> {
        if (params.trim().isEmpty()) {
            return Result.failure(IllegalArgumentException("Email cannot be empty"))
        }

        return if (isEmailValid(params)) {
            Result.success(null)
        } else {
            Result.failure(IllegalArgumentException("Invalid email format"))
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
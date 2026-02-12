package dev.espera.devtask.domain.usecases

import dev.espera.devtask.common.usecase.IUseCase
import dev.espera.devtask.domain.repositories.UsersRepository

data class LoginRequestParams(
    val email: String,
    val password: String,
)

class MakeLoginRequestUseCase(
    val usersRepository: UsersRepository,
) : IUseCase<Unit, LoginRequestParams> {
    override suspend fun invoke(params: LoginRequestParams): Result<Unit> {
        return usersRepository.login(params.email, params.password)
    }
}
package dev.espera.devtask.common.usecase

interface IUseCase<out Type, in Params> where Type : Any {
    suspend operator fun invoke(params: Params): Result<Type>
}
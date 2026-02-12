package dev.espera.devtask.common.usecase

interface IUseCase<out Type, in Params> {
    suspend operator fun invoke(params: Params): Result<Type>
}

interface IBaseUseCase<in Params, out Type> {
    operator fun invoke(params: Params): Result<Type>
}
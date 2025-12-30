package dev.espera.devtask.common.di

import dev.espera.devtask.data.dataModule
import dev.espera.devtask.domain.usecases.GetLoggedInUserUseCase
import dev.espera.devtask.domain.usecases.MakeLoginRequestUseCase
import dev.espera.devtask.domain.usecases.ValidateEmailUseCase
import dev.espera.devtask.features.auth.viewmodels.AuthViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val useCases = module {
    singleOf(::MakeLoginRequestUseCase)
    singleOf(::GetLoggedInUserUseCase)
    singleOf(::ValidateEmailUseCase)
}

val viewModels = module {
    viewModelOf(::AuthViewModel)
}

val appModule = module {
    includes(dataModule, useCases, viewModels)
}
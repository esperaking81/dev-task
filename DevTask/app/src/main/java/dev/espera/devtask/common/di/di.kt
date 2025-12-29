package dev.espera.devtask.common.di

import dev.espera.devtask.data.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule)
}
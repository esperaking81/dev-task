package dev.espera.devtask

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
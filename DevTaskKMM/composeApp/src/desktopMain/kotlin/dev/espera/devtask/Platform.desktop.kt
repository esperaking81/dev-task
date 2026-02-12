package dev.espera.devtask

class DesktopPlatform : Platform {
    override val name: String
        get() {
            val os = System.getProperty("os.name")?.lowercase() ?: "unknown"
            return when {
                os.contains("mac") -> "Mac OS"
                os.contains("win") -> "Windows"
                os.contains("linux") -> "Linux"
                else -> os
            }
        }
}

actual fun getPlatform(): Platform = DesktopPlatform()

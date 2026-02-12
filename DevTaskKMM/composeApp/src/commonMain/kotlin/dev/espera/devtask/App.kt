package dev.espera.devtask

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.espera.devtask.ui.screens.MainScreen
import dev.espera.devtask.ui.screens.TaskScreen
import dev.espera.devtask.ui.screens.WelcomeScreen
import dev.espera.devtask.ui.theme.AppTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
private data object WelcomeRoute : NavKey

@Serializable
private data object MainRoute : NavKey

@Serializable
private data class TaskRoute(val id: String) : NavKey {
    companion object
}

private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(WelcomeRoute::class, WelcomeRoute.serializer())
            subclass(MainRoute::class, MainRoute.serializer())
            subclass(TaskRoute::class, TaskRoute.serializer())
        }
    }
}

@Composable
@Preview
fun App() {
    AppTheme {
        val backStack = rememberNavBackStack(config, WelcomeRoute)

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<WelcomeRoute> {
                    WelcomeScreen {
                        backStack.add(MainRoute)
                    }
                }
                entry<MainRoute> {
                    MainScreen {
                        backStack.add(TaskRoute(id = "fake-id"))
                    }
                }
                entry<TaskRoute> {
                    TaskScreen {
                        backStack.removeLastOrNull()
                    }
                }
            }
        )
    }
}

package dev.espera.devtask

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.espera.devtask.common.di.appModule
import dev.espera.devtask.features.auth.screens.AuthScreen
import dev.espera.devtask.features.home.screens.HomeScreen
import dev.espera.devtask.ui.theme.DevTaskTheme
import org.koin.compose.KoinApplication

@Composable
fun DevTaskApp() {
    val backStack = remember { mutableStateListOf<Any>(AuthScreen) }

    KoinApplication(application = {
        modules(appModule)
    }){
        DevTaskTheme {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<AuthScreen> {
                        AuthScreen(
                            onLogInSuccessful = {
                                backStack.add(HomeScreen)
                            }
                        )
                    }
                    entry<HomeScreen> { HomeScreen() }
                },
                transitionSpec = {
                    // Slide in from right when navigating forward
                    slideInHorizontally(initialOffsetX = { it }) togetherWith
                            slideOutHorizontally(targetOffsetX = { -it })
                },
                popTransitionSpec = {
                    // Slide in from left when navigating back
                    slideInHorizontally(initialOffsetX = { -it }) togetherWith
                            slideOutHorizontally(targetOffsetX = { it })
                },
                predictivePopTransitionSpec = {
                    // Slide in from left when navigating back
                    slideInHorizontally(initialOffsetX = { -it }) togetherWith
                            slideOutHorizontally(targetOffsetX = { it })
                },
            )
        }
    }
}
package dev.espera.devtask.features.auth.viewmodels

import androidx.lifecycle.ViewModel

enum class AuthUiStatus {
    LOADING,
    SUCCESS,
    ERROR,
}

data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val currentTabIndex: Int = 0,
    val authTabs: List<String> = listOf("Log In", "Sign Up"),
)

class AuthViewModel : ViewModel() {

}
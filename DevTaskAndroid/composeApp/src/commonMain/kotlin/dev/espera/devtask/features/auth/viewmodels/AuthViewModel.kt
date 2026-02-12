package dev.espera.devtask.features.auth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.espera.devtask.domain.usecases.GetLoggedInUserUseCase
import dev.espera.devtask.domain.usecases.LoginRequestParams
import dev.espera.devtask.domain.usecases.MakeLoginRequestUseCase
import dev.espera.devtask.domain.usecases.ValidateEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val errorMessage: String? = null,
    val isProcessing: Boolean = false,
    val isUserLoggedIn: Boolean = false,
)

class AuthViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase,
    private val makeLoginRequestUseCase: MakeLoginRequestUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    init {
        viewModelScope.launch {
            getLoggedInUserUseCase.invoke().collect { user ->
                _uiState.update { state ->
                    state.copy(isUserLoggedIn = user != null)
                }
            }
        }
    }

    fun logIn(email: String, password: String) {
        if (!isValid(email)) return

        viewModelScope.launch {
            _uiState.update { it.copy(isProcessing = true, errorMessage = null) }

            val result = makeLoginRequestUseCase.invoke(LoginRequestParams(email, password))

            _uiState.update {
                if (result.isFailure) {
                    it.copy(errorMessage = result.exceptionOrNull()?.message, isProcessing = false)
                } else {
                    it.copy(isProcessing = false, isUserLoggedIn = true)
                }
            }
        }
    }

    private fun isValid(email: String): Boolean {
        val validationResult = validateEmailUseCase(email)
        if (validationResult.isFailure) {
            _uiState.update { state ->
                state.copy(errorMessage = validationResult.exceptionOrNull()?.message)
            }
            return false
        }

        return true
    }

    fun onErrorMessageShown() {
        _uiState.update { state ->
            state.copy(errorMessage = null)
        }
    }
}
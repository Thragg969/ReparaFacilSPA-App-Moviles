package com.example.reparafacilspa.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = LoginUIState(loading = true)

            try {
                if (email == "test@test.com" && pass == "1234") {
                    _uiState.value = LoginUIState(loading = false)
                } else {
                    _uiState.value = LoginUIState(
                        loading = false,
                        errorMessage = "Credenciales incorrectas"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = LoginUIState(
                    loading = false,
                    errorMessage = e.message ?: "Error inesperado"
                )
            }
        }
    }
}

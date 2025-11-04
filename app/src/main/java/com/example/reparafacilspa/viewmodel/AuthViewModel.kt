package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val token: String? = null
)

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)
            // acá iría llamado a tu API real
            if (email.isNotBlank() && pass.isNotBlank()) {
                _uiState.value = AuthUiState(token = "token-fake")
            } else {
                _uiState.value = AuthUiState(error = "Credenciales inválidas")
            }
        }
    }
}

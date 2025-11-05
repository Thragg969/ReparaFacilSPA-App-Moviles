package com.example.reparafacilspa.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.core.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = AuthRepository()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState(loading = true)
            try {
                val token = repo.login(email, pass)
                _uiState.value = LoginUiState(token = token)
            } catch (e: Exception) {
                _uiState.value = LoginUiState(
                    error = e.message ?: "Error al conectar"
                )
            }
        }
    }
}

package com.example.reparafacilspa.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.feature.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = AuthRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun doLogin(email: String, pass: String, onToken: (String) -> Unit) {
        viewModelScope.launch {
            _uiState.value = LoginUiState(isLoading = true)
            try {
                val res = repo.login(email, pass)
                // guardamos token hacia fuera
                onToken(res.token)
                // opcional: llamamos /auth/me para mostrar el user
                val me = repo.me(res.token)
                _uiState.value = LoginUiState(isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = LoginUiState(error = e.message ?: "Error al iniciar sesión")
            }
        }
    }
}

package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.ServiceLocator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val loggedIn: Boolean = false
)

class AuthViewModel : ViewModel() {
    private val repo = ServiceLocator.authRepository

    private val _ui = MutableStateFlow(AuthUiState())
    val ui: StateFlow<AuthUiState> = _ui.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _ui.value = _ui.value.copy(loading = true, error = null)
            val res = repo.login(email, password)
            _ui.value = if (res.isSuccess) {
                AuthUiState(loading = false, error = null, loggedIn = true)
            } else {
                AuthUiState(loading = false, error = res.exceptionOrNull()?.message)
            }
        }
    }
}

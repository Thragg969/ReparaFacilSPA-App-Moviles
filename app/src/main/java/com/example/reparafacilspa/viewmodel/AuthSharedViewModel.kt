package com.example.reparafacilspa.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.core.auth.AuthRepository
import com.example.reparafacilspa.ui.screens.login.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthSharedViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    // -------- PERFIL --------
    var name: String? = null
    var email: String? = null

    var avatarUri: String? = null
        private set

    var avatarBitmap: Bitmap? = null
        private set

    fun setUser(nombre: String, correo: String) {
        name = nombre
        email = correo
    }

    fun setAvatarUri(uri: String?) {
        avatarUri = uri
        avatarBitmap = null
    }

    fun setAvatarBitmap(bmp: Bitmap?) {
        avatarBitmap = bmp
        avatarUri = null
    }

    // -------- LOGIN --------
    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = LoginUIState(loading = true)

            if (email == "test@test.com" && pass == "1234") {
                this@AuthSharedViewModel.email = email
                this@AuthSharedViewModel.name = "Usuario ReparaFácil"

                _uiState.value = LoginUIState(loading = false)
                onSuccess()
            } else {
                _uiState.value = LoginUIState(
                    loading = false,
                    errorMessage = "Credenciales incorrectas"
                )
            }
        }
    }

    fun logout() {
        name = null
        email = null
        avatarUri = null
        avatarBitmap = null
        _uiState.value = LoginUIState()
    }
}

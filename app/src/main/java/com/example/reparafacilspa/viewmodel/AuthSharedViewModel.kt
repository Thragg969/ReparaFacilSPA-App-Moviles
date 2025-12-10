package com.example.reparafacilspa.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.core.auth.AuthRepository
import com.example.reparafacilspa.ui.screens.login.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel compartido para gestionar la autenticación y datos del usuario
 */
class AuthSharedViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    // ========== ESTADO UI ==========
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    // ========== DATOS DEL USUARIO ==========
    var name: String? = null
        private set

    var email: String? = null
        private set

    var role: String? = null
        private set

    var avatarUri: String? = null
        private set

    var avatarBitmap: Bitmap? = null
        private set

    // ========== MÉTODOS PÚBLICOS ==========

    /**
     * Establece los datos del usuario autenticado
     */
    fun setUser(nombre: String, correo: String, rol: String = "") {
        name = nombre
        email = correo
        role = rol
    }

    /**
     * Actualiza la URI del avatar
     */
    fun setAvatarUri(uri: String?) {
        avatarUri = uri
        avatarBitmap = null
    }

    /**
     * Actualiza el Bitmap del avatar
     */
    fun setAvatarBitmap(bmp: Bitmap?) {
        avatarBitmap = bmp
        avatarUri = null
    }

    /**
     * Realiza el login con credenciales mock (para testing)
     * @deprecated Usar el login real del backend en LoginScreen
     */
    @Deprecated("Usar backend real en LoginScreen")
    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = LoginUIState(loading = true)

            // Mock login (solo para testing)
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

    /**
     * Cierra sesión y limpia todos los datos del usuario
     */
    fun logout() {
        name = null
        email = null
        role = null
        avatarUri = null
        avatarBitmap = null
        _uiState.value = LoginUIState()
    }

    /**
     * Limpia el mensaje de error
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
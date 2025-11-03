package com.example.reparafacilspa.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Stub simple para que compile.
 * Si después manejas avatar en repositorio, reemplaza estas funciones.
 */
class PerfilViewModel : ViewModel() {
    private val _avatar: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val avatar: StateFlow<Uri?> = _avatar.asStateFlow()

    fun setAvatar(uri: Uri?) {
        _avatar.value = uri
    }
}

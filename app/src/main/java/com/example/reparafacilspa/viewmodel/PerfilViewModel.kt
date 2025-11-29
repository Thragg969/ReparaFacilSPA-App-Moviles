package com.example.reparafacilspa.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {

    private val _name = MutableStateFlow("Usuario")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("usuario@gmail.com")
    val email: StateFlow<String> = _email

    private val _phone = MutableStateFlow("+56 9 1234 5678")
    val phone: StateFlow<String> = _phone

    private val _avatarUri = MutableStateFlow<Uri?>(null)
    val avatarUri: StateFlow<Uri?> = _avatarUri

    fun editProfile() {
        // más adelante implementamos edición real
    }

    fun changeAvatar() {
        // más adelante implementamos selector de imágenes
    }
}

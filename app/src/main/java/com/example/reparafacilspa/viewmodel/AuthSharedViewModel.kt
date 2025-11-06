package com.example.reparafacilspa.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel

/**
 * ViewModel simple para compartir datos del usuario entre pantallas.
 */
class AuthSharedViewModel : ViewModel() {

    var name: String? = null
        private set

    var email: String? = null
        private set

    // si eligió una foto de la galería
    var avatarUri: String? = null
        private set

    // si sacó una foto con la cámara
    var avatarBitmap: Bitmap? = null
        private set

    fun setUser(name: String?, email: String?) {
        this.name = name
        this.email = email
    }

    fun setAvatarUri(uri: String?) {
        avatarUri = uri
        avatarBitmap = null
    }

    fun setAvatarBitmap(bitmap: Bitmap?) {
        avatarBitmap = bitmap
        avatarUri = null
    }

    fun logout() {
        name = null
        email = null
        avatarUri = null
        avatarBitmap = null
    }
}

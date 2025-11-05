package com.example.reparafacilspa.viewmodel

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel

class AuthSharedViewModel : ViewModel() {

    var name: String? = null
        private set

    var email: String? = null
        private set

    // para galería (uri en string)
    var avatarUri: String? = null
        private set

    // para cámara (bitmap)
    var avatarBitmap: ImageBitmap? = null
        private set

    fun setUser(name: String?, email: String?) {
        this.name = name
        this.email = email
    }

    fun setAvatarUri(uri: String?) {
        avatarUri = uri
        avatarBitmap = null   // si escogió galería, limpiamos cámara
    }

    fun setAvatarBitmap(bitmap: Bitmap?) {
        avatarBitmap = bitmap?.asImageBitmap()
        avatarUri = null      // si escogió cámara, limpiamos galería
    }
}

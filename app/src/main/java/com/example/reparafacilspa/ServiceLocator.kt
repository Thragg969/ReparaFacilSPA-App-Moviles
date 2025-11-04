package com.example.reparafacilspa

import com.example.reparafacilspa.core.auth.AuthRepository

object ServiceLocator {
    // repositorio mínimo para que compile
    val authRepository: AuthRepository by lazy {
        AuthRepository()
    }
}

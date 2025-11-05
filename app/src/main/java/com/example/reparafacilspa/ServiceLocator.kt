package com.example.reparafacilspa

import com.example.reparafacilspa.core.auth.AuthRepository

object ServiceLocator {
    val authRepository: AuthRepository by lazy {
        AuthRepository()
    }
}

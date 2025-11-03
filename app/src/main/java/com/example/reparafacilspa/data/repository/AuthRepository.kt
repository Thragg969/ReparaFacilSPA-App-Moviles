package com.example.reparafacilspa.data.repository

import com.example.reparafacilspa.core.local.SessionManager

class AuthRepository(
    private val session: SessionManager
) {
    // Stub de login: valida algo simple y guarda un token.
    suspend fun login(email: String, password: String): Result<Unit> = try {
        if (email.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("Email/Password vacíos")
        }
        session.setToken("FAKE_TOKEN_${email}")
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

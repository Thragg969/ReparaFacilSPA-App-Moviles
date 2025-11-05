package com.example.reparafacilspa.data.repository

import com.example.reparafacilspa.data.remote.LoginRequest
import com.example.reparafacilspa.data.remote.RetrofitClient
import com.example.reparafacilspa.data.remote.SignupRequest
import com.example.reparafacilspa.data.remote.LoginResponse
import com.example.reparafacilspa.data.remote.MeResponse


class AuthRepositoryData {

    /**
     * Inicia sesión con email y contraseña
     */
    suspend fun login(email: String, password: String): LoginResponse {
        return RetrofitClient.api.login(LoginRequest(email, password))
    }

    /**
     * Registra un nuevo usuario (nombre, email y contraseña)
     */
    suspend fun signup(name: String, email: String, password: String): LoginResponse {
        return RetrofitClient.api.signup(
            SignupRequest(
                email = email,
                password = password,
                name = name
            )
        )
    }

    /**
     * Obtiene la información del usuario autenticado
     */
    suspend fun me(token: String): MeResponse {
        return RetrofitClient.api.me("Bearer $token")
    }
}

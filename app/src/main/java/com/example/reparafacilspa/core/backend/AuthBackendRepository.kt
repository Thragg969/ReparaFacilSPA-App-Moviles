// app/src/main/java/com/example/reparafacilspa/core/backend/AuthBackendRepository.kt
package com.example.reparafacilspa.core.backend

import com.example.reparafacilspa.data.remote.*

class AuthBackendRepository {

    private val api = ReparaFacilBackendClient.api

    suspend fun login(email: String, password: String): LoginResponseDTO {
        val body = LoginRequestDTO(
            email = email,
            password = password
        )
        return api.login(body)
    }

    suspend fun register(
        email: String,
        password: String,
        role: String,
        nombre: String,
        telefono: String? = null,
        direccion: String? = null,
        especialidad: String? = null
    ): LoginResponseDTO {
        val body = RegisterRequestDTO(
            email = email,
            password = password,
            role = role,
            nombre = nombre,
            telefono = telefono,
            direccion = direccion,
            especialidad = especialidad
        )
        return api.register(body)
    }

    suspend fun getProfile(token: String): ProfileResponseDTO {
        return api.getProfile("Bearer $token")
    }
}
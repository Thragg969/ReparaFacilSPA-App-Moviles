package com.example.reparafacilspa.core.backend

import com.example.reparafacilspa.data.remote.LoginRequestDTO
import com.example.reparafacilspa.data.remote.LoginResponseDTO
import com.example.reparafacilspa.data.remote.ReparaFacilBackendClient

class AuthBackendRepository {

    private val api = ReparaFacilBackendClient.api

    suspend fun login(email: String, password: String): LoginResponseDTO {
        val body = LoginRequestDTO(
            email = email,
            password = password
        )
        return api.login(body)
    }
}

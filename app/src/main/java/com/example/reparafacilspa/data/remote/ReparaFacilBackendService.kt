package com.example.reparafacilspa.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

// 👉 adapta estos campos a lo que diga Swagger para /auth/login
data class LoginRequestDTO(
    val email: String,
    val password: String
)

// adapta los campos a la respuesta real del backend
data class LoginResponseDTO(
    val token: String,
    val nombre: String,
    val rol: String
)

interface ReparaFacilBackendService {

    @POST("auth/login")   // si en Swagger sale /auth/login
    suspend fun login(
        @Body body: LoginRequestDTO
    ): LoginResponseDTO
}

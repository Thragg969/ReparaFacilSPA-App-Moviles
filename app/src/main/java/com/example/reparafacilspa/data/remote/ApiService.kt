package com.example.reparafacilspa.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// ---------- DTOs ----------
data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val authToken: String
)

// puedes ajustar nombres según la API, esto es lo típico en Xano
data class SignupRequest(
    val email: String,
    val password: String,
    val name: String
)

data class MeResponse(
    val id: String?,
    val email: String?,
    val name: String?,
    val avatarUrl: String?
)

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @POST("auth/signup")
    suspend fun signup(@Body body: SignupRequest): LoginResponse

    @GET("auth/me")
    suspend fun me(
        @Header("Authorization") bearer: String
    ): MeResponse
}

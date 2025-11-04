package com.example.reparafacilspa.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// lo que ENVÍO al login
data class LoginRequest(
    val email: String,
    val password: String
)

// lo que la API del profe RESPONDE en /auth/login
data class LoginResponse(
    val authToken: String
)

// lo que responde /auth/me
data class MeResponse(
    val id: String?,
    val email: String?,
    val name: String?,
    val avatarUrl: String?
)

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("auth/me")
    suspend fun me(
        @Header("Authorization") bearer: String
    ): MeResponse
}

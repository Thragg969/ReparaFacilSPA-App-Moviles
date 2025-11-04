package com.example.reparafacilspa.core.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// adapta estos DTO a lo que devuelva tu API real
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String)
data class MeResponse(val name: String?, val email: String?)

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("auth/me")
    suspend fun me(@Header("Authorization") bearer: String): MeResponse
}

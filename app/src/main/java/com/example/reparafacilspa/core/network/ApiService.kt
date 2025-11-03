package com.example.reparafacilspa.core.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

data class LoginBody(val email: String, val password: String)
data class LoginResponse(val authToken: String)
data class MeResponse(val id: Int, val name: String?, val email: String)

interface ApiService {
    @POST("auth/login") suspend fun login(@Body body: LoginBody): LoginResponse
    @GET("auth/me") suspend fun me(@Header("Authorization") bearer: String): MeResponse
}

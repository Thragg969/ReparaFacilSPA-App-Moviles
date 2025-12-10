// app/src/main/java/com/example/reparafacilspa/data/remote/ReparaFacilBackendService.kt
package com.example.reparafacilspa.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// 📥 REQUEST
data class LoginRequestDTO(
    val email: String,
    val password: String
)

data class RegisterRequestDTO(
    val email: String,
    val password: String,
    val role: String,
    val nombre: String,
    val telefono: String? = null,
    val direccion: String? = null,
    val especialidad: String? = null,
    val certificaciones: List<String>? = null
)

// 📤 RESPONSE
data class LoginResponseDTO(
    val success: Boolean,
    val message: String,
    val data: AuthData
)

data class AuthData(
    val user: UserDTO,
    val access_token: String
)

data class UserDTO(
    val _id: String,
    val email: String,
    val role: String,
    val isActive: Boolean,
    val emailVerified: Boolean,
    val createdAt: String
)

data class ProfileResponseDTO(
    val success: Boolean,
    val data: UserDTO
)

interface ReparaFacilBackendService {

    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequestDTO
    ): LoginResponseDTO

    @POST("auth/register")
    suspend fun register(
        @Body body: RegisterRequestDTO
    ): LoginResponseDTO

    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponseDTO
}
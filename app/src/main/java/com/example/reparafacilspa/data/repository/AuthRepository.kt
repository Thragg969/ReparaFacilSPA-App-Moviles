package com.example.reparafacilspa.feature.auth

import com.example.reparafacilspa.core.network.ApiService
import com.example.reparafacilspa.core.network.LoginRequest

class AuthRepository(
    private val api: ApiService
) {
    suspend fun login(email: String, pass: String) = api.login(LoginRequest(email, pass))
    suspend fun me(token: String) = api.me("Bearer $token")
}

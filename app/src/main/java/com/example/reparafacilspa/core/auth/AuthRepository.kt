package com.example.reparafacilspa.core.auth

import com.example.reparafacilspa.data.remote.LoginRequest
import com.example.reparafacilspa.data.remote.RetrofitClient
import com.example.reparafacilspa.data.remote.SignupRequest

class AuthRepository {

    // LOGIN
    suspend fun login(email: String, password: String): String {
        val res = RetrofitClient.api.login(LoginRequest(email, password))
        return res.authToken
    }

    // REGISTRO
    suspend fun signup(name: String, email: String, password: String): String {
        val res = RetrofitClient.api.signup(
            SignupRequest(
                email = email,
                password = password,
                name = name
            )
        )
        return res.authToken
    }

    // PERFIL
    suspend fun me(token: String) =
        RetrofitClient.api.me("Bearer $token")
}

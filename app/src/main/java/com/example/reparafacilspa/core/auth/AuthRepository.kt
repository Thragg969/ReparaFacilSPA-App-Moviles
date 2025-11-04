package com.example.reparafacilspa.core.auth

import com.example.reparafacilspa.data.remote.LoginRequest
import com.example.reparafacilspa.data.remote.RetrofitClient

class AuthRepository {

    // login contra la API del profe
    suspend fun login(email: String, password: String): String {
        val res = RetrofitClient.api.login(LoginRequest(email, password))
        // res.authToken es lo que dice la rúbrica
        return res.authToken
    }

    // obtener perfil
    suspend fun me(token: String) =
        RetrofitClient.api.me("Bearer $token")
}

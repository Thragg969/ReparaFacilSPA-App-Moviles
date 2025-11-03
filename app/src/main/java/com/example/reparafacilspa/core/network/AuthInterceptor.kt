package com.example.reparafacilspa.core.network

import com.example.reparafacilspa.core.local.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor que adjunta el header Authorization si hay token guardado.
 */
class AuthInterceptor(
    private val session: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = runBlocking { session.getToken() } // bloquear corto para leer DataStore
        val req = if (!token.isNullOrBlank()) {
            original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else original
        return chain.proceed(req)
    }
}

// app/src/main/java/com/example/reparafacilspa/data/remote/ReparaFacilBackendClient.kt
package com.example.reparafacilspa.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ReparaFacilBackendClient {

    // 🔥 CAMBIAR ESTA URL POR LA DE TU RENDER
    private const val BASE_URL = "https://reparafacil-api-222.onrender.com"
    // Ejemplo: "https://reparafacil-api.onrender.com/api/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: ReparaFacilBackendService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReparaFacilBackendService::class.java)
    }
}
package com.example.reparafacilspa.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReparaFacilBackendClient {

    // Backend NestJS corriendo en tu PC en puerto 3000
    private const val BASE_URL = "http://10.0.2.2:3000/"

    val api: ReparaFacilBackendService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // OJO: tiene que ser ::class.java, no ::class
            .create(ReparaFacilBackendService::class.java)
    }
}

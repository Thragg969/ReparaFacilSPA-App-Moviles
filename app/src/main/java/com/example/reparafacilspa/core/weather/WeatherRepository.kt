package com.example.reparafacilspa.core.weather

import com.example.reparafacilspa.data.remote.WeatherRetrofitClient
import com.example.reparafacilspa.data.remote.WeatherResponse
import com.example.reparafacilspa.BuildConfig

class WeatherRepository {

    suspend fun getWeatherByCity(city: String): WeatherResponse {
        val apiKey = BuildConfig.OPENWEATHER_API_KEY
        return WeatherRetrofitClient.api.getWeatherByCity(
            city = city,
            apiKey = apiKey
        )
    }
}

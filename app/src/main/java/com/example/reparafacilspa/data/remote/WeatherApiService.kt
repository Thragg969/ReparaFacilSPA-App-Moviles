package com.example.reparafacilspa.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// Respuesta simplificada de OpenWeather
data class WeatherResponse(
    val main: MainInfo,
    val weather: List<WeatherInfo>,
    val name: String // nombre de la ciudad
)

data class MainInfo(
    val temp: Double // temperatura en Kelvin
)

data class WeatherInfo(
    val description: String
)

interface WeatherApiService {

    // Ejemplo: /weather?q=Santiago,CL&appid=API_KEY&units=metric
    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric", // para que llegue en °C
        @Query("lang") lang: String = "es"
    ): WeatherResponse
}

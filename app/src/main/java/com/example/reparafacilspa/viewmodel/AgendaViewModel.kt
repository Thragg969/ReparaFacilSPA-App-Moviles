package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.core.weather.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AgendaItem(
    val servicioId: Int,
    val inicio: String,
    val fin: String,
    val direccion: String
)

data class WeatherUiState(
    val loading: Boolean = false,
    val ciudad: String = "",
    val temperatura: String = "",
    val descripcion: String = "",
    val error: String? = null
)


class AgendaViewModel : ViewModel() {

    // 🔹 Estado de la agenda (lo que ya tenías)
    private val _agenda = MutableStateFlow<List<AgendaItem>>(emptyList())
    val agenda: StateFlow<List<AgendaItem>> = _agenda

    // si tienes algo como agendar(...)
    suspend fun agendar(item: AgendaItem) {
        _agenda.value = _agenda.value + item
    }

    // 🔹 Estado del clima (API externa)
    private val weatherRepository = WeatherRepository()

    private val _weatherState = MutableStateFlow(WeatherUiState())
    val weatherState: StateFlow<WeatherUiState> = _weatherState

    fun loadWeatherForCity(city: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState(loading = true)

            try {
                val response = weatherRepository.getWeatherByCity(city)

                val temp = response.main.temp // ya viene en °C por units=metric
                val desc = response.weather.firstOrNull()?.description ?: "Sin info"

                _weatherState.value = WeatherUiState(
                    loading = false,
                    ciudad = response.name,
                    temperatura = "${temp.toInt()} °C",
                    descripcion = desc.replaceFirstChar { it.uppercase() }
                )
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState(
                    loading = false,
                    error = e.message ?: "Error al obtener el clima"
                )
            }
        }
    }
}

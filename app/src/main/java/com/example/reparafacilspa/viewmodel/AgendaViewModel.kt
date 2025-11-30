package com.example.reparafacilspa.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.core.weather.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AgendaEvento(
    val id: Int,
    val servicioId: Int?,
    val titulo: String,
    val fecha: String,
    val estado: String = "Programado"
)

data class WeatherUiState(
    val loading: Boolean = false,
    val ciudad: String = "",
    val temperatura: String = "",
    val descripcion: String = "",
    val error: String? = null
)

class AgendaViewModel : ViewModel() {

    private val eventosList = mutableStateListOf(
        AgendaEvento(1, 1, "Revisión de refrigerador", "2025-01-05 15:00", "Programado"),
        AgendaEvento(2, 2, "Instalación eléctrica", "2025-01-02 11:00", "Realizado"),
        AgendaEvento(3, null, "Visita general", "2025-01-10 09:00", "Programado")
    )

    val eventos: List<AgendaEvento> get() = eventosList

    private var nextId = 4

    // Weather
    private val weatherRepository = WeatherRepository()

    private val _weatherState = MutableStateFlow(WeatherUiState())
    val weatherState: StateFlow<WeatherUiState> = _weatherState

    fun loadWeatherForCity(city: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState(loading = true)

            try {
                val response = weatherRepository.getWeatherByCity(city)
                val temp = response.main.temp
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

    fun crearEvento(servicioId: Int?, titulo: String, fecha: String) {
        eventosList.add(
            AgendaEvento(
                id = nextId++,
                servicioId = servicioId,
                titulo = titulo,
                fecha = fecha,
                estado = "Programado"
            )
        )
    }

    fun cancelarEvento(id: Int) {
        eventosList.replaceAll {
            if (it.id == id) it.copy(estado = "Cancelado") else it
        }
    }

    fun reagendarEvento(id: Int, nuevaFecha: String) {
        eventosList.replaceAll {
            if (it.id == id) it.copy(fecha = nuevaFecha) else it
        }
    }

    fun getEvento(id: Int): AgendaEvento? {
        return eventosList.find { it.id == id }
    }
}
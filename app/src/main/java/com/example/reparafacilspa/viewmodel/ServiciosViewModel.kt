package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ServicioUi(
    val id: Long,
    val titulo: String,
    val descripcion: String
)

class ServiciosViewModel : ViewModel() {

    private val _servicios = MutableStateFlow<List<ServicioUi>>(emptyList())
    val servicios: StateFlow<List<ServicioUi>> = _servicios

    fun agregar(titulo: String, descripcion: String) {
        viewModelScope.launch {
            val nuevo = ServicioUi(
                id = System.currentTimeMillis(),
                titulo = titulo,
                descripcion = descripcion
            )
            _servicios.value = _servicios.value + nuevo
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            _servicios.value = _servicios.value.filterNot { it.id == id }
        }
    }
}

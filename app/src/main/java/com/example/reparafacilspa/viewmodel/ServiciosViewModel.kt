package com.example.reparafacilspa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ServicioUi(
    val id: Int,
    val titulo: String,
    val descripcion: String
)

class ServiciosViewModel : ViewModel() {

    // lista visible para la pantalla
    private val _servicios = mutableStateListOf<ServicioUi>()
    val servicios: List<ServicioUi> get() = _servicios

    // mensaje para mostrar en snackbar cuando se crea algo
    var lastMessage by mutableStateOf<String?>(null)
        private set

    private var nextId = 1

    fun addServicio(titulo: String, descripcion: String) {
        _servicios.add(
            ServicioUi(
                id = nextId++,
                titulo = titulo,
                descripcion = descripcion
            )
        )
        lastMessage = "Servicio creado exitosamente"
    }

    fun clearMessage() {
        lastMessage = null
    }
}

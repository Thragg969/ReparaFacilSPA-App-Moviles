package com.example.reparafacilspa.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class TecnicoUi(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Float,
    val telefono: String,
    val disponible: Boolean = true
)

class TecnicoViewModel : ViewModel() {

    private val _tecnicos = mutableStateListOf(
        TecnicoUi(1, "Carlos Muñoz", "Refrigeración", 4.8f, "+56 9 8765 4321", true),
        TecnicoUi(2, "María González", "Electricidad", 4.9f, "+56 9 7654 3210", true),
        TecnicoUi(3, "Pedro Sánchez", "Plomería", 4.5f, "+56 9 6543 2109", true),
        TecnicoUi(4, "Ana Torres", "Electrodomésticos", 4.7f, "+56 9 5432 1098", false),
        TecnicoUi(5, "Jorge Ramírez", "Gasfitería", 4.6f, "+56 9 4321 0987", true)
    )

    val tecnicos: List<TecnicoUi> get() = _tecnicos

    var lastMessage by mutableStateOf<String?>(null)
        private set

    fun getTecnico(id: Int): TecnicoUi? {
        return _tecnicos.find { it.id == id }
    }

    fun getTecnicosDisponibles(): List<TecnicoUi> {
        return _tecnicos.filter { it.disponible }
    }

    fun clearMessage() {
        lastMessage = null
    }
}
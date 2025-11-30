package com.example.reparafacilspa.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ReparacionUi(
    val id: Int,
    val servicioId: Int,
    val tecnicoNombre: String,
    val descripcion: String,
    val fecha: String,
    val estado: String,
    val costo: Int
)

class ReparacionViewModel : ViewModel() {

    private val _reparaciones = mutableStateListOf(
        ReparacionUi(
            1,
            1,
            "Carlos Muñoz",
            "Reparación de refrigerador Samsung",
            "2024-11-15",
            "Completada",
            45000
        ),
        ReparacionUi(
            2,
            2,
            "María González",
            "Instalación eléctrica en cocina",
            "2024-11-20",
            "En proceso",
            65000
        ),
        ReparacionUi(
            3,
            1,
            "Pedro Sánchez",
            "Mantenimiento lavadora LG",
            "2024-11-10",
            "Completada",
            35000
        )
    )

    val reparaciones: List<ReparacionUi> get() = _reparaciones

    var lastMessage by mutableStateOf<String?>(null)
        private set

    private var nextId = 4

    fun crearReparacion(
        servicioId: Int,
        tecnicoNombre: String,
        descripcion: String,
        fecha: String
    ) {
        _reparaciones.add(
            ReparacionUi(
                id = nextId++,
                servicioId = servicioId,
                tecnicoNombre = tecnicoNombre,
                descripcion = descripcion,
                fecha = fecha,
                estado = "Programada",
                costo = 0
            )
        )
        lastMessage = "Reparación agendada exitosamente"
    }

    fun getReparacion(id: Int): ReparacionUi? {
        return _reparaciones.find { it.id == id }
    }

    fun getReparacionesCompletadas(): List<ReparacionUi> {
        return _reparaciones.filter { it.estado == "Completada" }
    }

    fun clearMessage() {
        lastMessage = null
    }
}
package com.example.reparafacilspa.data.model

data class Tecnico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val telefono: String,
    val activo: Boolean = true
)

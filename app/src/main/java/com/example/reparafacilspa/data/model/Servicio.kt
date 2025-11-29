package com.example.reparafacilspa.data.model

data class Servicio(
    val id: Long = 0,
    val titulo: String = "",
    val descripcion: String = "",
    val direccion: String = "",
    val fecha: String = "",
    val problema: String = "",
    val estado: String = "",
    val ubicacion: String? = null
)

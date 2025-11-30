package com.example.reparafacilspa.data.model

data class AgendaEvento(
    val id: Int,
    val servicioId: Int,
    val fecha: String,
    val hora: String,
    val direccion: String
)

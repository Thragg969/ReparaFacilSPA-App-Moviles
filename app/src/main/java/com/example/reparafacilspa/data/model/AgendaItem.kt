package com.example.reparafacilspa.data.model

data class AgendaItem(
    val id: Int,
    val servicioId: Int,
    val tecnicoId: Int,
    val inicio: String,   // "dd/MM/yyyy HH:mm"
    val fin: String,
    val direccion: String
)

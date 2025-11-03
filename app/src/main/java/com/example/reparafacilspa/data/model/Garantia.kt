package com.example.reparafacilspa.data.model

data class Garantia(
    val id: Int,
    val servicioId: Int,
    val fechaInicio: String, // "dd/MM/yyyy"
    val fechaFin: String,
    val condiciones: String
)

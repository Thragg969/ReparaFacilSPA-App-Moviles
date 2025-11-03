package com.example.reparafacilspa.data.repository

import kotlinx.coroutines.delay

/**
 * Stub temporal para compilar: no usa FakeDataSource.
 */
class GarantiaRepository {
    data class Garantia(
        val id: Int,
        val servicioId: Int,
        val fechaInicio: String,
        val fechaFin: String,
        val condiciones: String
    )

    private val garantias = mutableListOf<Garantia>()
    private var seq = 1

    suspend fun registrar(
        servicioId: Int,
        fechaInicio: String,
        fechaFin: String,
        condiciones: String
    ): Garantia {
        delay(100)
        val g = Garantia(seq++, servicioId, fechaInicio, fechaFin, condiciones)
        garantias.add(g)
        return g
    }

    suspend fun listar(): List<Garantia> {
        delay(100)
        return garantias.toList()
    }
}

package com.example.reparafacilspa.data.repository

import kotlinx.coroutines.delay

/**
 * Stub temporal para compilar: no usa FakeDataSource.
 * Reemplázalo luego por llamadas reales a Retrofit.
 */
class AgendaRepository {
    data class Cita(
        val id: Int,
        val servicioId: Int,
        val inicio: String,
        val fin: String,
        val direccion: String
    )

    private val citas = mutableListOf<Cita>()
    private var seq = 1

    suspend fun agendar(
        servicioId: Int,
        inicio: String,
        fin: String,
        direccion: String
    ): Cita {
        delay(100)
        val nueva = Cita(seq++, servicioId, inicio, fin, direccion)
        citas.add(nueva)
        return nueva
    }

    suspend fun listar(): List<Cita> {
        delay(100)
        return citas.toList()
    }
}

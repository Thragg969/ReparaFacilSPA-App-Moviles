package com.example.reparafacilspa.data.repository

import com.example.reparafacilspa.data.model.Servicio
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ServicioRepository {
    private val lock = Mutex()
    private val data = mutableListOf(
        Servicio(1, "Revisión general", "Diagnóstico básico"),
        Servicio(2, "Reparación electrodoméstico", "Cambio de piezas menores")
    )

    suspend fun listar(): List<Servicio> {
        delay(100)
        return lock.withLock { data.toList() }
    }

    suspend fun crear(titulo: String, descripcion: String): Servicio {
        delay(100)
        return lock.withLock {
            val nuevo = Servicio((data.maxOfOrNull { it.id } ?: 0) + 1, titulo, descripcion)
            data.add(nuevo)
            nuevo
        }
    }
}

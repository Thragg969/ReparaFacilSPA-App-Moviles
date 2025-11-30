package com.example.reparafacilspa.data

import com.example.reparafacilspa.data.model.Servicio
import com.example.reparafacilspa.data.model.AgendaEvento

class AppRepository {

    private var servicioIdCounter = 1
    private var agendaIdCounter = 1

    private val servicios = mutableListOf<Servicio>()
    private val agenda = mutableListOf<AgendaEvento>()

    // ---------------- SERVICIOS ------------------

    fun getServicios(): List<Servicio> = servicios

    fun createServicio(titulo: String, descripcion: String): Servicio {
        val nuevo = Servicio(
            id = servicioIdCounter++,
            titulo = titulo,
            descripcion = descripcion
        )
        servicios.add(nuevo)
        return nuevo
    }

    fun updateServicio(servicio: Servicio) {
        val index = servicios.indexOfFirst { it.id == servicio.id }
        if (index != -1) servicios[index] = servicio
    }

    fun deleteServicio(id: Int) {
        servicios.removeAll { it.id == id }
    }

    fun getServicio(id: Int): Servicio? =
        servicios.find { it.id == id }

    // ---------------- AGENDA ------------------

    fun getAgenda(): List<AgendaEvento> = agenda

    fun createEvento(servicio: Servicio, fecha: String, hora: String): AgendaEvento {
        val nuevo = AgendaEvento(
            id = agendaIdCounter++,
            servicioId = servicio.id,
            fecha = fecha,
            hora = hora,
            direccion = servicio.direccion ?: "Dirección no registrada"
        )
        agenda.add(nuevo)
        return nuevo
    }

    fun deleteEvento(id: Int) {
        agenda.removeAll { it.id == id }
    }

    fun updateEvento(evento: AgendaEvento) {
        val index = agenda.indexOfFirst { it.id == evento.id }
        if (index != -1) agenda[index] = evento
    }
}

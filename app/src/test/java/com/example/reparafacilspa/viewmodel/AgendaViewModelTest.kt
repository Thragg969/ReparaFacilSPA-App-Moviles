package com.example.reparafacilspa.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AgendaViewModelTest {

    private lateinit var viewModel: AgendaViewModel

    @Before
    fun setup() {
        viewModel = AgendaViewModel()
    }

    @Test
    fun `viewModel se inicializa con eventos`() {
        // Assert
        assertTrue(viewModel.eventos.isNotEmpty())
        assertEquals(3, viewModel.eventos.size)
    }

    @Test
    fun `crear evento incrementa la lista`() {
        // Arrange
        val cantidadInicial = viewModel.eventos.size

        // Act
        viewModel.crearEvento(
            servicioId = 5,
            titulo = "Visita de prueba",
            fecha = "2024-12-05 14:00"
        )

        // Assert
        assertEquals(cantidadInicial + 1, viewModel.eventos.size)
    }

    @Test
    fun `nuevo evento tiene estado Programado por defecto`() {
        // Act
        viewModel.crearEvento(
            servicioId = 1,
            titulo = "Test evento",
            fecha = "2024-12-10 10:00"
        )

        // Assert
        val ultimoEvento = viewModel.eventos.last()
        assertEquals("Programado", ultimoEvento.estado)
        assertEquals("Test evento", ultimoEvento.titulo)
    }

    @Test
    fun `cancelar evento cambia el estado a Cancelado`() {
        // Arrange
        val eventoId = 1
        val eventoOriginal = viewModel.getEvento(eventoId)
        assertNotNull(eventoOriginal)
        assertEquals("Programado", eventoOriginal?.estado)

        // Act
        viewModel.cancelarEvento(eventoId)

        // Assert
        val eventoCancelado = viewModel.getEvento(eventoId)
        assertEquals("Cancelado", eventoCancelado?.estado)
    }

    @Test
    fun `reagendar evento actualiza la fecha`() {
        // Arrange
        val eventoId = 1
        val nuevaFecha = "2025-01-15 16:00"

        // Act
        viewModel.reagendarEvento(eventoId, nuevaFecha)

        // Assert
        val eventoReagendado = viewModel.getEvento(eventoId)
        assertEquals(nuevaFecha, eventoReagendado?.fecha)
    }

    @Test
    fun `getEvento retorna evento correcto por ID`() {
        // Act
        val evento = viewModel.getEvento(1)

        // Assert
        assertNotNull(evento)
        assertEquals(1, evento?.id)
    }

    @Test
    fun `getEvento retorna null para ID inexistente`() {
        // Act
        val evento = viewModel.getEvento(999)

        // Assert
        assertNull(evento)
    }

    @Test
    fun `evento puede crearse sin servicioId asociado`() {
        // Act
        viewModel.crearEvento(
            servicioId = null,
            titulo = "Evento sin servicio",
            fecha = "2024-12-20 09:00"
        )

        // Assert
        val ultimoEvento = viewModel.eventos.last()
        assertNull(ultimoEvento.servicioId)
        assertEquals("Evento sin servicio", ultimoEvento.titulo)
    }
}
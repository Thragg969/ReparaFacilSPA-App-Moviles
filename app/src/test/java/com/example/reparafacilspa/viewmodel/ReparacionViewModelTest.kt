package com.example.reparafacilspa.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ReparacionViewModelTest {

    private lateinit var viewModel: ReparacionViewModel

    @Before
    fun setup() {
        viewModel = ReparacionViewModel()
    }

    @Test
    fun `viewModel se inicializa con reparaciones`() {
        // Assert
        assertTrue(viewModel.reparaciones.isNotEmpty())
        assertEquals(3, viewModel.reparaciones.size)
    }

    @Test
    fun `crear reparacion incrementa la lista`() {
        // Arrange
        val cantidadInicial = viewModel.reparaciones.size

        // Act
        viewModel.crearReparacion(
            servicioId = 5,
            tecnicoNombre = "Test Tecnico",
            descripcion = "Reparacion de prueba",
            fecha = "2024-12-01"
        )

        // Assert
        assertEquals(cantidadInicial + 1, viewModel.reparaciones.size)
        assertTrue(viewModel.lastMessage?.contains("exitosamente") == true)
    }

    @Test
    fun `nueva reparacion tiene estado Programada`() {
        // Act
        viewModel.crearReparacion(
            servicioId = 1,
            tecnicoNombre = "Pedro Lopez",
            descripcion = "Test reparacion",
            fecha = "2024-12-05"
        )

        // Assert
        val ultimaReparacion = viewModel.reparaciones.last()
        assertEquals("Programada", ultimaReparacion.estado)
        assertEquals(0, ultimaReparacion.costo)
    }

    @Test
    fun `getReparacion retorna reparacion correcta por ID`() {
        // Act
        val reparacion = viewModel.getReparacion(1)

        // Assert
        assertNotNull(reparacion)
        assertEquals(1, reparacion?.id)
    }

    @Test
    fun `getReparacion retorna null para ID inexistente`() {
        // Act
        val reparacion = viewModel.getReparacion(999)

        // Assert
        assertNull(reparacion)
    }

    @Test
    fun `getReparacionesCompletadas retorna solo completadas`() {
        // Act
        val completadas = viewModel.getReparacionesCompletadas()

        // Assert
        assertTrue(completadas.all { it.estado == "Completada" })
        assertEquals(2, completadas.size)
    }

    @Test
    fun `clearMessage limpia el mensaje`() {
        // Arrange
        viewModel.crearReparacion(1, "Test", "Desc", "2024-12-01")
        assertNotNull(viewModel.lastMessage)

        // Act
        viewModel.clearMessage()

        // Assert
        assertNull(viewModel.lastMessage)
    }

    @Test
    fun `reparaciones completadas tienen costo mayor a cero`() {
        // Act
        val completadas = viewModel.getReparacionesCompletadas()

        // Assert
        assertTrue(completadas.all { it.costo > 0 })
    }
}
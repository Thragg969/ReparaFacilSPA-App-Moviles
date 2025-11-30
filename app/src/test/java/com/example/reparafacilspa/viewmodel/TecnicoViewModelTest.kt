package com.example.reparafacilspa.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TecnicoViewModelTest {

    private lateinit var viewModel: TecnicoViewModel

    @Before
    fun setup() {
        viewModel = TecnicoViewModel()
    }

    @Test
    fun `viewModel se inicializa con tecnicos`() {
        // Assert
        assertTrue(viewModel.tecnicos.isNotEmpty())
        assertEquals(5, viewModel.tecnicos.size)
    }

    @Test
    fun `getTecnico retorna tecnico correcto por ID`() {
        // Act
        val tecnico = viewModel.getTecnico(1)

        // Assert
        assertNotNull(tecnico)
        assertEquals(1, tecnico?.id)
        assertEquals("Carlos Munoz", tecnico?.nombre)
    }

    @Test
    fun `getTecnico retorna null para ID inexistente`() {
        // Act
        val tecnico = viewModel.getTecnico(999)

        // Assert
        assertNull(tecnico)
    }

    @Test
    fun `getTecnicosDisponibles retorna solo disponibles`() {
        // Act
        val disponibles = viewModel.getTecnicosDisponibles()

        // Assert
        assertTrue(disponibles.all { it.disponible })
        assertEquals(4, disponibles.size) // Ana Torres no esta disponible
    }

    @Test
    fun `todos los tecnicos tienen calificacion entre 0 y 5`() {
        // Assert
        assertTrue(viewModel.tecnicos.all { it.calificacion in 0f..5f })
    }

    @Test
    fun `clearMessage limpia el mensaje`() {
        // Arrange
        viewModel.lastMessage = "Test message"

        // Act
        viewModel.clearMessage()

        // Assert
        assertNull(viewModel.lastMessage)
    }

    @Test
    fun `tecnicos tienen diferentes especialidades`() {
        // Act
        val especialidades = viewModel.tecnicos.map { it.especialidad }.distinct()

        // Assert
        assertTrue(especialidades.size > 1)
        assertTrue(especialidades.contains("Refrigeracion"))
        assertTrue(especialidades.contains("Electricidad"))
    }
}
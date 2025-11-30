package com.example.reparafacilspa.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ServiciosViewModelTest {

    private lateinit var viewModel: ServiciosViewModel

    @Before
    fun setup() {
        viewModel = ServiciosViewModel()
    }

    @Test
    fun `viewModel se inicializa con servicios del repositorio`() {
        // Assert
        assertNotNull(viewModel.servicios)
    }

    @Test
    fun `agregar servicio incrementa la lista`() {
        // Arrange
        val cantidadInicial = viewModel.servicios.size

        // Act
        viewModel.addServicio(
            titulo = "Reparacion de TV",
            descripcion = "Servicio tecnico para televisor Samsung"
        )

        // Assert
        assertEquals(cantidadInicial + 1, viewModel.servicios.size)
        assertEquals("Servicio creado correctamente.", viewModel.lastMessage)
    }

    @Test
    fun `eliminar servicio reduce la lista`() {
        // Arrange
        viewModel.addServicio("Test", "Test descripcion")
        val cantidadInicial = viewModel.servicios.size
        val idAEliminar = viewModel.servicios.last().id

        // Act
        viewModel.deleteServicio(idAEliminar)

        // Assert
        assertEquals(cantidadInicial - 1, viewModel.servicios.size)
        assertNull(viewModel.getServicio(idAEliminar))
        assertEquals("Servicio eliminado", viewModel.lastMessage)
    }

    @Test
    fun `getServicio retorna servicio correcto por ID`() {
        // Arrange
        val servicio = viewModel.addServicio("Test Servicio", "Descripcion test")

        // Act
        val encontrado = viewModel.getServicio(servicio.id)

        // Assert
        assertNotNull(encontrado)
        assertEquals(servicio.id, encontrado?.id)
        assertEquals("Test Servicio", encontrado?.titulo)
    }

    @Test
    fun `getServicio retorna null para ID inexistente`() {
        // Act
        val servicio = viewModel.getServicio(99999)

        // Assert
        assertNull(servicio)
    }

    @Test
    fun `updateServicio actualiza correctamente`() {
        // Arrange
        val servicio = viewModel.addServicio("Original", "Descripcion original")
        val actualizado = servicio.copy(titulo = "Actualizado")

        // Act
        viewModel.updateServicio(actualizado)

        // Assert
        val encontrado = viewModel.getServicio(servicio.id)
        assertEquals("Actualizado", encontrado?.titulo)
        assertEquals("Servicio actualizado", viewModel.lastMessage)
    }

    @Test
    fun `clearMessage limpia el mensaje`() {
        // Arrange
        viewModel.addServicio("Test", "Test desc")
        assertNotNull(viewModel.lastMessage)

        // Act
        viewModel.clearMessage()

        // Assert
        assertNull(viewModel.lastMessage)
    }

    @Test
    fun `addServicio retorna el servicio creado`() {
        // Act
        val servicio = viewModel.addServicio("Nuevo", "Descripcion")

        // Assert
        assertNotNull(servicio)
        assertEquals("Nuevo", servicio.titulo)
        assertEquals("Descripcion", servicio.descripcion)
    }
}
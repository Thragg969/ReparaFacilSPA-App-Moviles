package com.example.reparafacilspa.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GarantiaViewModelTest {

    private lateinit var viewModel: GarantiaViewModel

    @Before
    fun setup() {
        viewModel = GarantiaViewModel()
    }

    @Test
    fun `viewModel se inicializa con garantias`() {
        // Assert
        assertTrue(viewModel.garantias.isNotEmpty())
        assertEquals(2, viewModel.garantias.size)
    }

    @Test
    fun `crear garantia incrementa la lista`() {
        // Arrange
        val cantidadInicial = viewModel.garantias.size

        // Act
        viewModel.crearGarantia(
            reparacionId = 5,
            descripcion = "Garantia de test",
            fechaInicio = "2024-12-01",
            meses = 3
        )

        // Assert
        assertEquals(cantidadInicial + 1, viewModel.garantias.size)
        assertTrue(viewModel.lastMessage?.contains("exitosamente") == true)
    }

    @Test
    fun `nueva garantia tiene estado Vigente por defecto`() {
        // Act
        viewModel.crearGarantia(
            reparacionId = 1,
            descripcion = "Test garantia",
            fechaInicio = "2024-12-01",
            meses = 6
        )

        // Assert
        val ultimaGarantia = viewModel.garantias.last()
        assertEquals("Vigente", ultimaGarantia.estado)
        assertEquals("Mano de obra y repuestos", ultimaGarantia.cobertura)
    }

    @Test
    fun `calculo de fecha de vencimiento suma meses correctamente`() {
        // Act
        viewModel.crearGarantia(
            reparacionId = 1,
            descripcion = "Test",
            fechaInicio = "2024-01-15",
            meses = 3
        )

        // Assert
        val garantia = viewModel.garantias.last()
        assertEquals("2024-04-15", garantia.fechaVencimiento)
    }

    @Test
    fun `calculo de fecha maneja cambio de año correctamente`() {
        // Act
        viewModel.crearGarantia(
            reparacionId = 1,
            descripcion = "Test",
            fechaInicio = "2024-11-15",
            meses = 3
        )

        // Assert
        val garantia = viewModel.garantias.last()
        assertEquals("2025-02-15", garantia.fechaVencimiento)
    }

    @Test
    fun `getGarantia retorna garantia correcta por ID`() {
        // Act
        val garantia = viewModel.getGarantia(1)

        // Assert
        assertNotNull(garantia)
        assertEquals(1, garantia?.id)
    }

    @Test
    fun `getGarantia retorna null para ID inexistente`() {
        // Act
        val garantia = viewModel.getGarantia(999)

        // Assert
        assertNull(garantia)
    }

    @Test
    fun `getGarantiasVigentes retorna solo vigentes`() {
        // Act
        val vigentes = viewModel.getGarantiasVigentes()

        // Assert
        assertTrue(vigentes.all { it.estado == "Vigente" })
        assertEquals(2, vigentes.size)
    }

    @Test
    fun `clearMessage limpia el mensaje`() {
        // Arrange
        viewModel.crearGarantia(1, "Test", "2024-12-01", 3)
        assertNotNull(viewModel.lastMessage)

        // Act
        viewModel.clearMessage()

        // Assert
        assertNull(viewModel.lastMessage)
    }
}
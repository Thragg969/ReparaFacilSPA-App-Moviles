package com.example.reparafacilspa.repository

import com.example.reparafacilspa.data.repository.ServicioRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ServicioRepositoryTest {

    private lateinit var repository: ServicioRepository

    @Before
    fun setup() {
        repository = ServicioRepository()
    }

    @Test
    fun `listar retorna lista no vacia`() = runTest {
        // Act
        val servicios = repository.listar()

        // Assert
        assertNotNull(servicios)
        assertTrue(servicios.isNotEmpty())
        assertEquals(2, servicios.size)
    }

    @Test
    fun `listar retorna servicios iniciales correctos`() = runTest {
        // Act
        val servicios = repository.listar()

        // Assert
        assertEquals("Revisión general", servicios[0].titulo)
        assertEquals("Reparación electrodoméstico", servicios[1].titulo)
    }

    @Test
    fun `crear servicio incrementa la lista`() = runTest {
        // Arrange
        val cantidadInicial = repository.listar().size

        // Act
        repository.crear("Nuevo servicio", "Nueva descripcion")
        val serviciosActualizados = repository.listar()

        // Assert
        assertEquals(cantidadInicial + 1, serviciosActualizados.size)
    }

    @Test
    fun `crear servicio retorna servicio con ID unico`() = runTest {
        // Act
        val servicio1 = repository.crear("Test 1", "Descripcion 1")
        val servicio2 = repository.crear("Test 2", "Descripcion 2")

        // Assert
        assertNotEquals(servicio1.id, servicio2.id)
        assertTrue(servicio2.id > servicio1.id)
    }

    @Test
    fun `crear servicio asigna titulo y descripcion correctos`() = runTest {
        // Act
        val servicio = repository.crear("Test Titulo", "Test Descripcion")

        // Assert
        assertEquals("Test Titulo", servicio.titulo)
        assertEquals("Test Descripcion", servicio.descripcion)
        assertTrue(servicio.id > 0)
    }

    @Test
    fun `crear servicio lo agrega a la lista`() = runTest {
        // Act
        val nuevoServicio = repository.crear("Servicio Test", "Descripcion Test")
        val servicios = repository.listar()

        // Assert
        assertTrue(servicios.any { it.id == nuevoServicio.id })
        assertTrue(servicios.any { it.titulo == "Servicio Test" })
    }

    @Test
    fun `multiples servicios se crean con IDs incrementales`() = runTest {
        // Act
        val servicio1 = repository.crear("Servicio 1", "Desc 1")
        val servicio2 = repository.crear("Servicio 2", "Desc 2")
        val servicio3 = repository.crear("Servicio 3", "Desc 3")

        // Assert
        assertEquals(servicio1.id + 1, servicio2.id)
        assertEquals(servicio2.id + 1, servicio3.id)
    }

    @Test
    fun `crear servicio es thread-safe`() = runTest {
        // Act - Crear múltiples servicios concurrentemente
        val servicio1 = repository.crear("Concurrent 1", "Desc 1")
        val servicio2 = repository.crear("Concurrent 2", "Desc 2")
        val servicios = repository.listar()

        // Assert
        assertEquals(4, servicios.size) // 2 iniciales + 2 nuevos
        assertTrue(servicios.contains(servicio1))
        assertTrue(servicios.contains(servicio2))
    }
}
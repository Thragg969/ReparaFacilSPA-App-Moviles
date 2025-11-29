package com.example.reparafacilspa.repository

import com.example.reparafacilspa.core.auth.AuthRepository
import com.example.reparafacilspa.data.remote.ApiService
import com.example.reparafacilspa.data.remote.LoginRequest
import com.example.reparafacilspa.data.remote.LoginResponse
import com.example.reparafacilspa.data.remote.RetrofitClient
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

class AuthRepositoryTest {

    private lateinit var apiMock: ApiService
    private lateinit var repository: AuthRepository

    @Before
    fun setUp() {
        // Mockeamos el objeto RetrofitClient.api
        mockkObject(RetrofitClient)
        apiMock = mockk()

        io.mockk.every { RetrofitClient.api } returns apiMock

        // El repo real usa RetrofitClient.api internamente
        repository = AuthRepository()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `login exitoso devuelve el token correcto`() = runBlocking {
        // Arrange
        val email = "test@example.com"
        val password = "123456"
        val expectedToken = "token_123"

        coEvery {
            apiMock.login(LoginRequest(email, password))
        } returns LoginResponse(authToken = expectedToken)

        // Act
        val token = repository.login(email, password)

        // Assert
        assertEquals(expectedToken, token)
        coVerify(exactly = 1) {
            apiMock.login(LoginRequest(email, password))
        }
    }

    @Test
    fun `login con error de red lanza excepcion`() = runBlocking {
        // Arrange
        val email = "test@example.com"
        val password = "123456"

        coEvery {
            apiMock.login(LoginRequest(email, password))
        } throws Exception("Network error")

        // Act
        var exception: Exception? = null
        try {
            repository.login(email, password)
        } catch (e: Exception) {
            exception = e
        }

        // Assert
        assertNotNull(exception)
        assertTrue(exception?.message?.contains("Network") == true)
    }
}

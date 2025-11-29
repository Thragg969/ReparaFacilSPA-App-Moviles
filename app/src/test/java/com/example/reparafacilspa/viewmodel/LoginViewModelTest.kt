package com.example.reparafacilspa.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.reparafacilspa.core.auth.AuthRepository
import com.example.reparafacilspa.ui.screens.login.LoginUiState
import com.example.reparafacilspa.ui.screens.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockkConstructor
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        // Usamos un dispatcher de test para reemplazar Main
        Dispatchers.setMain(testDispatcher)
        // Cada vez que el ViewModel haga AuthRepository(), vamos a mockearlo
        mockkConstructor(AuthRepository::class)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `login exitoso actualiza uiState con token`() = runBlocking {
        // Arrange
        val expectedToken = "token_123"

        coEvery {
            anyConstructed<AuthRepository>().login("test@example.com", "123456")
        } returns expectedToken

        val viewModel = LoginViewModel()

        // Act
        viewModel.login("test@example.com", "123456")

        // Como usamos UnconfinedTestDispatcher, la corrutina ya se ejecutó
        val state: LoginUiState = viewModel.uiState.value

        // Assert
        assertFalse(state.loading)
        assertEquals(expectedToken, state.token)
        assertNull(state.error)
    }

    @Test
    fun `login con error actualiza uiState con mensaje de error`() = runBlocking {
        // Arrange
        coEvery {
            anyConstructed<AuthRepository>().login("test@example.com", "wrongpass")
        } throws Exception("Credenciales inválidas")

        val viewModel = LoginViewModel()

        // Act
        viewModel.login("test@example.com", "wrongpass")

        val state: LoginUiState = viewModel.uiState.value

        // Assert
        assertFalse(state.loading)
        assertNull(state.token)
        assertEquals("Credenciales inválidas", state.error)
    }
}

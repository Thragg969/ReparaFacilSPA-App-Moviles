package com.example.reparafacilspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reparafacilspa.ui.screens.login.LoginScreen
import com.example.reparafacilspa.ui.screens.login.RegisterScreen
import com.example.reparafacilspa.ui.screens.perfil.PerfilScreen
import com.example.reparafacilspa.ui.screens.servicio.ServicioCreateScreen
import com.example.reparafacilspa.ui.screens.servicio.ServiciosScreen
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel

@Composable
fun AppNav() {
    val nav = rememberNavController()
    // VM compartido SOLO para auth/perfil
    val authVm: AuthSharedViewModel = viewModel()

    NavHost(navController = nav, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onLogin = { _, _ ->
                    nav.navigate("servicios") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBack = null,
                onRegister = { nav.navigate("register") },
                authVm = authVm
            )
        }

        composable("register") {
            RegisterScreen(
                onRegistered = {
                    // volvemos al login después de crear la cuenta
                    nav.popBackStack()
                },
                onBack = { nav.popBackStack() },
                authVm = authVm
            )
        }

        composable("servicios") {
            ServiciosScreen(
                onAdd = { nav.navigate("servicios/create") },
                onPerfil = { nav.navigate("perfil") }
            )
        }

        composable("servicios/create") {
            ServicioCreateScreen(
                onDone = { nav.popBackStack() }
            )
        }

        composable("perfil") {
            PerfilScreen(
                onBack = { nav.popBackStack() },
                authVm = authVm
            )
        }
    }
}

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
import com.example.reparafacilspa.viewmodel.ServiciosViewModel

@Composable
fun AppNav() {
    val nav = rememberNavController()

    // VM compartido de auth
    val authVm: AuthSharedViewModel = viewModel()

    // VM compartido de servicios
    val serviciosVm: ServiciosViewModel = viewModel()

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
                onRegistered = { nav.popBackStack() },
                onBack = { nav.popBackStack() },
                authVm = authVm
            )
        }

        composable("servicios") {
            ServiciosScreen(
                viewModel = serviciosVm,
                onAdd = { nav.navigate("servicios/create") },
                onPerfil = { nav.navigate("perfil") }
            )
        }

        composable("servicios/create") {
            ServicioCreateScreen(
                viewModel = serviciosVm,
                onDone = { nav.popBackStack() }
            )
        }

        composable("perfil") {
            PerfilScreen(
                onBack = { nav.popBackStack() },
                onLogout = {
                    nav.navigate("login") {
                        popUpTo("servicios") { inclusive = true }
                    }
                },
                authVm = authVm
            )
        }
    }
}

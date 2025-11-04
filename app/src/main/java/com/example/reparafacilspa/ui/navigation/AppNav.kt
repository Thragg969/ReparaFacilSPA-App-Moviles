package com.example.reparafacilspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reparafacilspa.ui.screens.login.LoginScreen
import com.example.reparafacilspa.ui.screens.perfil.PerfilScreen
import com.example.reparafacilspa.ui.screens.servicio.ServicioCreateScreen
import com.example.reparafacilspa.ui.screens.servicio.ServiciosScreen



@Composable
fun AppNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLogin = { _, _ ->
                    // cuando loguea, lo mando a la lista
                    navController.navigate("servicios") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBack = null
            )
        }


        composable("servicios") {
            ServiciosScreen(
                onAdd = { navController.navigate("servicios/create") },
                onPerfil = { navController.navigate("perfil") }
            )
        }
        composable("servicios/create") {
            ServicioCreateScreen(
                onDone = { navController.popBackStack() }
            )
        }
        composable("perfil") {
            PerfilScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}


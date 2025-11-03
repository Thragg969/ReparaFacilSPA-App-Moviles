package com.example.reparafacilspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reparafacilspa.ui.screens.login.LoginScreen
import com.example.reparafacilspa.ui.screens.servicio.ServicioCreateScreen
import com.example.reparafacilspa.ui.screens.servicio.ServicioListScreen
import com.example.reparafacilspa.ui.screens.servicio.ServicioUI

@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = "login") {

        // --- LOGIN ---
        composable("login") {
            LoginScreen(
                onLogin = { _, _ -> nav.navigate("servicios") },
                onBack = null
            )
        }

        // --- LISTA DE SERVICIOS ---
        composable("servicios") {
            val demo = listOf(
                ServicioUI(id = "1", titulo = "Revisión general", descripcion = "Diagnóstico básico"),
                ServicioUI(id = "2", titulo = "Reparación electrodoméstico", descripcion = "Cambio de piezas menores")
            )

            ServicioListScreen(
                servicios = demo,
                onAdd = { nav.navigate("servicios/new") },
                // onItem recibe UN ServicioUI (tipo explícito para que no falle la inferencia)
                onItem = { _: ServicioUI ->
                    // TODO: navega a detalle si lo implementas
                },
                onBack = { nav.popBackStack() }
            )
        }

        // --- CREAR SERVICIO ---
        composable("servicios/new") {
            // onSave(titulo, descripcion, lat?, lng?)  -> ajustado a la firma con 4 params
            ServicioCreateScreen(
                onSave = { titulo: String, descripcion: String, lat: String?, lng: String? ->
                    // TODO: persiste si corresponde…
                    nav.popBackStack()
                },
                onBack = { nav.popBackStack() }
            )
        }
    }
}



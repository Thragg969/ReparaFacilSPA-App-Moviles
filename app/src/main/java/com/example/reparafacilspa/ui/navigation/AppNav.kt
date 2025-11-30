package com.example.reparafacilspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.reparafacilspa.ui.screens.agenda.AgendaScreen
import com.example.reparafacilspa.ui.screens.home.HomeScreen
import com.example.reparafacilspa.ui.screens.login.LoginScreen
import com.example.reparafacilspa.ui.screens.login.RegisterScreen
import com.example.reparafacilspa.ui.screens.perfil.PerfilScreen
import com.example.reparafacilspa.ui.screens.servicio.ServicioCreateScreen
import com.example.reparafacilspa.ui.screens.servicio.ServiciosScreen

import com.example.reparafacilspa.viewmodel.AgendaViewModel
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel
import com.example.reparafacilspa.viewmodel.ServiciosViewModel

@Composable
fun AppNavigation(nav: NavHostController) {

    // ---------- ViewModels Compartidos ----------
    val authVm: AuthSharedViewModel = viewModel()
    val serviciosVm: ServiciosViewModel = viewModel()
    val agendaVm: AgendaViewModel = viewModel()

    NavHost(
        navController = nav,
        startDestination = "login"
    ) {

        // ---------- LOGIN ----------
        composable("login") {
            LoginScreen(
                onLogin = { _, _ ->
                    nav.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBack = null,
                onRegister = { nav.navigate("register") },
                authVm = authVm
            )
        }

        // ---------- REGISTER ----------
        composable("register") {
            RegisterScreen(
                onRegistered = { nav.popBackStack() },
                onBack = { nav.popBackStack() },
                authVm = authVm
            )
        }

        // ---------- HOME (Nuevo Dashboard 2.0) ----------
        composable("home") {
            HomeScreen(
                onGoServicios = { nav.navigate("servicios") },
                onGoPerfil = { nav.navigate("perfil") },
                serviciosVm = serviciosVm,
                agendaVm = agendaVm
            )
        }

        // ---------- SERVICIOS ----------
        composable("servicios") {
            ServiciosScreen(
                viewModel = serviciosVm,
                onAdd = { nav.navigate("servicio_create") },
                onPerfil = { nav.navigate("perfil") }
            )
        }

        // ---------- CREAR SERVICIO ----------
        composable("servicio_create") {
            ServicioCreateScreen(
                viewModel = serviciosVm,
                onDone = { nav.popBackStack() }
            )
        }

        // ---------- PERFIL ----------
        composable("perfil") {
            PerfilScreen(
                onBack = { nav.navigate("home") },
                onLogout = {
                    authVm.logout()
                    nav.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                authVm = authVm
            )
        }

        // ---------- AGENDA ----------
        composable("agenda") {
            AgendaScreen(vm = agendaVm)
        }
    }
}
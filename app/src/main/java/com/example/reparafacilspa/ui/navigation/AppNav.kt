package com.example.reparafacilspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.reparafacilspa.ui.screens.agenda.AgendaScreen
import com.example.reparafacilspa.ui.screens.agenda.AgendarEventoScreen
import com.example.reparafacilspa.ui.screens.agenda.AgendaDetailScreen
import com.example.reparafacilspa.ui.screens.agenda.ReagendarDialog
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

    val authVm: AuthSharedViewModel = viewModel()
    val serviciosVm: ServiciosViewModel = viewModel()
    val agendaVm: AgendaViewModel = viewModel()

    NavHost(
        navController = nav,
        startDestination = "login"
    ) {

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

        composable("register") {
            RegisterScreen(
                onRegistered = { nav.popBackStack() },
                onBack = { nav.popBackStack() },
                authVm = authVm
            )
        }

        composable("home") {
            HomeScreen(
                onGoServicios = { nav.navigate("servicios") },
                onGoPerfil = { nav.navigate("perfil") },
                serviciosVm = serviciosVm,
                agendaVm = agendaVm
            )
        }

        composable("servicios") {
            ServiciosScreen(
                viewModel = serviciosVm,
                onAdd = { nav.navigate("servicio_create") },
                onPerfil = { nav.navigate("perfil") }
            )
        }

        composable("servicio_create") {
            ServicioCreateScreen(
                viewModel = serviciosVm,
                onDone = { nav.popBackStack() }
            )
        }

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

        composable("agenda") {
            AgendaScreen(
                nav = nav,
                vm = agendaVm
            )
        }

        composable("agendar") {
            AgendarEventoScreen(
                nav = nav,
                vm = agendaVm
            )
        }
        composable(
            route = "agenda_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            AgendaDetailScreen(nav, agendaVm, id)
        }

        composable(
            route = "reagendar/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ReagendarDialog(
                onDismiss = { nav.popBackStack() },
                onConfirm = { fecha ->
                    agendaVm.reagendarEvento(id, fecha)
                    nav.popBackStack()
                }
            )
        }
    }
}
package com.example.reparafacilspa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.example.reparafacilspa.ui.screens.servicio.ServicioDetailScreen
import com.example.reparafacilspa.ui.screens.tecnico.TecnicosDisponiblesScreen
import com.example.reparafacilspa.ui.screens.reparacion.MisReparacionesScreen
import com.example.reparafacilspa.ui.screens.garantia.GarantiasScreen
import com.example.reparafacilspa.ui.screens.agenda.AgendarVisitaScreen

import com.example.reparafacilspa.viewmodel.AgendaViewModel
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel
import com.example.reparafacilspa.viewmodel.ServiciosViewModel
import com.example.reparafacilspa.viewmodel.TecnicoViewModel
import com.example.reparafacilspa.viewmodel.ReparacionViewModel
import com.example.reparafacilspa.viewmodel.GarantiaViewModel

@Composable
fun AppNav(
    authVm: AuthSharedViewModel,
    serviciosVm: ServiciosViewModel,
    agendaVm: AgendaViewModel,
    tecnicoVm: TecnicoViewModel,
    reparacionVm: ReparacionViewModel,
    garantiaVm: GarantiaViewModel
) {
    val nav = rememberNavController()

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
                nav = nav,
                authVm = authVm,
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

        composable(
            route = "servicio_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ServicioDetailScreen(
                nav = nav,
                vm = serviciosVm,
                id = id
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

        // ========== NUEVAS PANTALLAS ==========

        composable("tecnicos") {
            TecnicosDisponiblesScreen(
                nav = nav,
                viewModel = tecnicoVm
            )
        }

        composable(
            route = "agendar_visita/{tecnicoId}",
            arguments = listOf(navArgument("tecnicoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val tecnicoId = backStackEntry.arguments?.getInt("tecnicoId") ?: 0
            AgendarVisitaScreen(
                nav = nav,
                agendaVm = agendaVm,
                tecnicoVm = tecnicoVm,
                reparacionVm = reparacionVm,
                tecnicoId = tecnicoId
            )
        }

        composable("reparaciones") {
            MisReparacionesScreen(
                nav = nav,
                viewModel = reparacionVm
            )
        }

        composable("garantias") {
            GarantiasScreen(
                nav = nav,
                viewModel = garantiaVm
            )
        }

        // ========== AGENDA ==========

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
package com.example.reparafacilspa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.reparafacilspa.ui.navigation.AppNav
import com.example.reparafacilspa.ui.theme.ReparaFacilTheme
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel
import com.example.reparafacilspa.viewmodel.ServiciosViewModel
import com.example.reparafacilspa.viewmodel.AgendaViewModel
import com.example.reparafacilspa.viewmodel.TecnicoViewModel
import com.example.reparafacilspa.viewmodel.ReparacionViewModel
import com.example.reparafacilspa.viewmodel.GarantiaViewModel

class MainActivity : ComponentActivity() {

    // ViewModels
    private val authVm: AuthSharedViewModel by viewModels()
    private val serviciosVm: ServiciosViewModel by viewModels()
    private val agendaVm: AgendaViewModel by viewModels()
    private val tecnicoVm: TecnicoViewModel by viewModels()
    private val reparacionVm: ReparacionViewModel by viewModels()
    private val garantiaVm: GarantiaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ReparaFacilTheme {
                AppNav(
                    authVm = authVm,
                    serviciosVm = serviciosVm,
                    agendaVm = agendaVm,
                    tecnicoVm = tecnicoVm,
                    reparacionVm = reparacionVm,
                    garantiaVm = garantiaVm
                )
            }
        }
    }
}
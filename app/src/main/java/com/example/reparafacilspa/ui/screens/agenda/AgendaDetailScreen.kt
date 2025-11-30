package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaDetailScreen(
    nav: NavController,
    vm: AgendaViewModel,
    id: Int
) {
    val evento = vm.getEvento(id)

    if (evento == null) {
        Text("Evento no encontrado")
        return
    }

    var showReagendar by remember { mutableStateOf(false) }
    var showCancelar by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Evento") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { pad ->

        Column(
            Modifier
                .padding(pad)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text("Título: ${evento.titulo}", style = MaterialTheme.typography.headlineSmall)
            Text("Fecha: ${evento.fecha}")
            Text("Estado: ${evento.estado}")

            Button(
                onClick = { showReagendar = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.DateRange, null)
                Spacer(Modifier.width(8.dp))
                Text("Reagendar")
            }

            OutlinedButton(
                onClick = { showCancelar = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Close, null)
                Spacer(Modifier.width(8.dp))
                Text("Cancelar evento")
            }
        }
    }

    if (showReagendar) {
        ReagendarDialog(
            onDismiss = { showReagendar = false },
            onConfirm = { nuevaFecha ->
                vm.reagendarEvento(id, nuevaFecha)
                showReagendar = false
            }
        )
    }

    if (showCancelar) {
        CancelarDialog(
            onDismiss = { showCancelar = false },
            onConfirm = {
                vm.cancelarEvento(id)
                showCancelar = false
            }
        )
    }
}
package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarEventoScreen(
    nav: NavController,
    vm: AgendaViewModel
) {
    var titulo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agendar Evento") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
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

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título del evento") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (ej: 2025-01-20 14:00)") },
                modifier = Modifier.fillMaxWidth()
            )

            if (error != null) {
                Text(error!!, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    if (titulo.isBlank() || fecha.isBlank()) {
                        error = "Completa todos los campos"
                    } else {
                        vm.crearEvento(null, titulo, fecha)
                        nav.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}

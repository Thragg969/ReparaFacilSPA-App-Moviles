package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.AgendaViewModel
import com.example.reparafacilspa.viewmodel.TecnicoViewModel
import com.example.reparafacilspa.viewmodel.ReparacionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarVisitaScreen(
    nav: NavController,
    agendaVm: AgendaViewModel,
    tecnicoVm: TecnicoViewModel,
    reparacionVm: ReparacionViewModel,
    tecnicoId: Int
) {
    val tecnico = tecnicoVm.getTecnico(tecnicoId)

    var titulo by remember { mutableStateOf("Visita técnica") }
    var descripcion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("2024-12-05") }
    var hora by remember { mutableStateOf("10:00") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agendar Visita") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Información del técnico
            if (tecnico != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Técnico seleccionado",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = tecnico.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = tecnico.especialidad,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            // Formulario
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título de la visita") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción del problema") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                }
            )

            OutlinedTextField(
                value = hora,
                onValueChange = { hora = it },
                label = { Text("Hora (HH:MM)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // Crear evento en agenda (concatenar fecha + hora)
                    val fechaCompleta = "$fecha $hora"
                    agendaVm.crearEvento(
                        servicioId = 0,
                        titulo = titulo,
                        fecha = fechaCompleta
                    )

                    // Crear reparación
                    reparacionVm.crearReparacion(
                        servicioId = 0,
                        tecnicoNombre = tecnico?.nombre ?: "Desconocido",
                        descripcion = descripcion,
                        fecha = fecha
                    )

                    nav.navigate("home") {
                        popUpTo("home") { inclusive = false }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titulo.isNotBlank() && descripcion.isNotBlank()
            ) {
                Text("Confirmar Agendamiento")
            }
        }
    }
}
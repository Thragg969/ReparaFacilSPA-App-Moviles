package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(vm: AgendaViewModel) {

    val eventos by vm.agenda.collectAsState()
    val clima by vm.weatherState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agenda") })
        }
    ) { pad ->

        Column(
            Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            // ---------- CLIMA ----------
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(Modifier.padding(16.dp)) {

                    if (clima.loading) {
                        CircularProgressIndicator()
                    } else if (clima.error != null) {
                        Text("Error al cargar clima: ${clima.error}")
                    } else {
                        Text("Ciudad: ${clima.ciudad}", style = MaterialTheme.typography.titleMedium)
                        Text("Temperatura: ${clima.temperatura}")
                        Text("Descripción: ${clima.descripcion}")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ---------- AGENDA ----------
            if (eventos.isEmpty()) {
                Text("No hay eventos agendados.")
            } else {
                LazyColumn {
                    items(eventos) { e ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Servicio: ${e.servicioId}", style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(6.dp))
                                Text("Inicio: ${e.inicio}")
                                Text("Fin: ${e.fin}")
                                Spacer(Modifier.height(6.dp))
                                Text("Dirección: ${e.direccion}")
                            }
                        }
                    }
                }
            }
        }
    }
}

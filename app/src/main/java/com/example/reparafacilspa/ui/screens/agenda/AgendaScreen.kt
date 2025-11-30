package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(nav: NavController, vm: AgendaViewModel) {

    val eventos by remember { derivedStateOf { vm.eventos } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agenda") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { nav.navigate("agendar") }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { pad ->

        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp)
        ) {

            if (eventos.isEmpty()) {
                Text("No hay eventos aún.")
                return@Column
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(eventos) { ev ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { nav.navigate("agenda_detail/${ev.id}") },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(ev.titulo, style = MaterialTheme.typography.titleMedium)
                                Icon(Icons.Default.KeyboardArrowRight, null)
                            }

                            Spacer(Modifier.height(6.dp))

                            Text("📅 ${ev.fecha}")
                            Text("Estado: ${ev.estado}")
                        }
                    }
                }
            }
        }
    }
}

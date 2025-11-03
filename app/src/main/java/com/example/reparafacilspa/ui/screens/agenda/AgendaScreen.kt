package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(vm: AgendaViewModel = viewModel()) {
    val data by vm.agenda.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Agenda") }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            if (data.isEmpty()) {
                Text("No hay eventos agendados.")
            } else {
                LazyColumn {
                    items(data) { item ->
                        Card(Modifier.padding(bottom = 8.dp)) {
                            Column(Modifier.padding(12.dp)) {
                                Text("Servicio #${item.servicioId}")
                                Text("Inicio: ${item.inicio}")
                                Text("Fin: ${item.fin}")
                                Text("Dirección: ${item.direccion}")
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.reparafacilspa.viewmodel.AgendaViewModel
import com.example.reparafacilspa.viewmodel.WeatherUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(vm: AgendaViewModel = viewModel()) {
    // 🔹 Estado de la agenda (lo que ya tenías)
    val data by vm.agenda.collectAsState()

    // 🔹 Estado del clima (API externa)
    val weatherState by vm.weatherState.collectAsState()

    // Llamamos a la API de clima una vez al entrar
    LaunchedEffect(Unit) {
        vm.loadWeatherForCity("Santiago,CL")  // después lo puedes hacer dinámico
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Agenda") }) }
    ) { pad ->
        Column(
            Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // ---------- CARD DE CLIMA (API EXTERNA) ----------
            WeatherCard(state = weatherState)

            Spacer(modifier = Modifier.height(16.dp))

            // ---------- LISTA DE EVENTOS (LO TUYO ORIGINAL) ----------
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

@Composable
fun WeatherCard(state: WeatherUiState) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        when {
            state.loading -> {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Cargando clima...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(Modifier.fillMaxSize())
                }
            }

            state.error != null -> {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "No se pudo obtener el clima",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        state.error ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            else -> {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Clima en ${state.ciudad}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.temperatura,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = state.descripcion,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

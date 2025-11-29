package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reparafacilspa.viewmodel.GarantiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GarantiaScreen(vm: GarantiaViewModel) {

    val data by vm.garantias.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Garantías") }) }
    ) { pad ->

        Column(Modifier.padding(pad).padding(16.dp)) {

            if (data.isEmpty()) {
                Text("No hay garantías registradas.")
                return@Column
            }

            LazyColumn {
                items(data) { g ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text("Servicio #${g.servicioId}", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(6.dp))
                            Text("Vigencia: ${g.fechaInicio} → ${g.fechaFin}")
                            Spacer(Modifier.height(6.dp))
                            Text("Condiciones: ${g.condiciones}")
                        }
                    }
                }
            }
        }
    }
}

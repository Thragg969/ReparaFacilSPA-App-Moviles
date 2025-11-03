package com.example.reparafacilspa.ui.screens.garantia

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reparafacilspa.viewmodel.GarantiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GarantiaScreen(vm: GarantiaViewModel = viewModel()) {
    val data by vm.garantias.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Garantías") }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            if (data.isEmpty()) {
                Text("No hay garantías registradas.")
            } else {
                LazyColumn {
                    items(data) { g ->
                        Card(Modifier.padding(bottom = 8.dp)) {
                            Column(Modifier.padding(12.dp)) {
                                Text("Servicio #${g.servicioId}")
                                Text("Vigencia: ${g.fechaInicio} → ${g.fechaFin}")
                                Text("Condiciones: ${g.condiciones}")
                            }
                        }
                    }
                }
            }
        }
    }
}

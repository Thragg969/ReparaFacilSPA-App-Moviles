package com.example.reparafacilspa.ui.screens.servicio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reparafacilspa.ui.theme.RFScaffold

data class ServicioUI(val id: String, val titulo: String, val descripcion: String)

@Composable
fun ServicioListScreen(
    servicios: List<ServicioUI>,
    onAdd: () -> Unit,
    onItem: (ServicioUI) -> Unit,
    onBack: (() -> Unit)? = null
) {
    RFScaffold(
        title = "Servicios",
        onBack = onBack,
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Filled.Add, contentDescription = "Nuevo")
            }
        }
    ) { mod ->
        LazyColumn(
            modifier = mod.fillMaxSize().padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(servicios) { s ->
                ElevatedCard(onClick = { onItem(s) }) {
                    Column(Modifier.padding(16.dp)) {
                        Text(s.titulo, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(s.descripcion, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

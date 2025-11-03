package com.example.reparafacilspa.ui.screens.servicio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.reparafacilspa.core.local.LocationHelper
import com.example.reparafacilspa.ui.theme.RFScaffold

@Composable
fun ServicioCreateScreen(
    onBack: (() -> Unit)? = null,
    onSave: (titulo: String, descripcion: String, lat: String?, lng: String?) -> Unit = { _, _, _, _ -> }
) {
    val context = LocalContext.current

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var lat by remember { mutableStateOf<String?>(null) }
    var lng by remember { mutableStateOf<String?>(null) }

    RFScaffold(title = "Nuevo servicio", onBack = onBack) { mod ->
        Column(
            modifier = mod
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    LocationHelper.requestSingleUpdate(context) { la: Double?, ln: Double? ->
                        lat = la?.toString()
                        lng = ln?.toString()
                    }
                }) { Text("Usar ubicación") }

                if (!lat.isNullOrBlank() && !lng.isNullOrBlank()) {
                    Text("($lat, $lng)")
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { onSave(titulo.trim(), descripcion.trim(), lat, lng) },
                enabled = titulo.isNotBlank() && descripcion.isNotBlank()
            ) { Text("Guardar") }
        }
    }
}

package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReagendarDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var fecha by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Reagendar evento") },
        text = {
            Column {
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Nueva fecha") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (fecha.isNotBlank()) onConfirm(fecha)
                }
            ) { Text("Guardar") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

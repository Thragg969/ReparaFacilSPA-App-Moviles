package com.example.reparafacilspa.ui.screens.agenda

import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun CancelarDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cancelar evento") },
        text = { Text("¿Seguro deseas cancelar este evento?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Sí, cancelar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}

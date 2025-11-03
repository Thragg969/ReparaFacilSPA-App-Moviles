package com.example.reparafacilspa.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(goPerfil: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("🏠 Home", style = MaterialTheme.typography.headlineSmall)
            Text("App dibujando OK. Navega para ver Perfil.")
            Button(onClick = goPerfil) { Text("Ir a Perfil") }
        }
    }
}

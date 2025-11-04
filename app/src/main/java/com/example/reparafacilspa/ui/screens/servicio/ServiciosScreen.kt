package com.example.reparafacilspa.ui.screens.servicio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reparafacilspa.viewmodel.ServiciosViewModel

@Composable
fun ServiciosScreen(
    onAdd: () -> Unit,
    onPerfil: () -> Unit,
    vm: ServiciosViewModel = viewModel()
) {
    val servicios by vm.servicios.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Servicios") },
                actions = {
                    IconButton(onClick = onPerfil) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Text("+")
            }
        }
    ) { pad ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pad)
        ) {
            if (servicios.isEmpty()) {
                Text(
                    "No hay servicios aún",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn {
                    items(servicios) { s ->
                        ListItem(
                            headlineContent = { Text(s.titulo) },
                            supportingContent = { Text(s.descripcion) },
                            trailingContent = {
                                IconButton(onClick = { vm.eliminar(s.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar"
                                    )
                                }
                            }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

package com.example.reparafacilspa.ui.screens.servicio

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.ServiciosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicioEditScreen(
    nav: NavController,
    vm: ServiciosViewModel,
    id: Int
) {
    val original = vm.getServicio(id)

    if (original == null) {
        Text("Servicio no encontrado")
        return
    }

    var titulo by remember { mutableStateOf(original.titulo) }
    var descripcion by remember { mutableStateOf(original.descripcion) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Servicio") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { pad ->

        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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

            Button(
                onClick = {
                    vm.updateServicio(
                        original.copy(titulo = titulo, descripcion = descripcion)
                    )
                    nav.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }
        }
    }
}

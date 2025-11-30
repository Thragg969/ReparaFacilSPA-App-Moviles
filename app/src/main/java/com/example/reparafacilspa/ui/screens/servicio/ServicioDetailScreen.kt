package com.example.reparafacilspa.ui.screens.servicio

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.ServiciosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicioDetailScreen(
    nav: NavController,
    vm: ServiciosViewModel,
    id: Int
) {
    val servicio = vm.getServicio(id)

    if (servicio == null) {
        Text("Servicio no encontrado.")
        return
    }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Servicio") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { pad ->

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { 100 }),
            modifier = Modifier.padding(pad).padding(20.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // Card principal
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(Modifier.padding(20.dp)) {

                        Text(
                            text = servicio.titulo,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(servicio.descripcion)
                    }
                }

                // Botón para agendar
                Button(
                    onClick = { nav.navigate("agendar/${servicio.id}") },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Agendar visita")
                }

                // Botón editar
                OutlinedButton(
                    onClick = { nav.navigate("servicio_edit/${servicio.id}") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Editar servicio")
                }

                // Botón eliminar
                OutlinedButton(
                    onClick = {
                        vm.deleteServicio(servicio.id)
                        nav.popBackStack()
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
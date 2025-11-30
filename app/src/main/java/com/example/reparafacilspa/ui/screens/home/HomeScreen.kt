@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.reparafacilspa.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reparafacilspa.viewmodel.ServiciosViewModel
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@Composable
fun HomeScreen(
    onGoServicios: () -> Unit,
    onGoPerfil: () -> Unit,
    serviciosVm: ServiciosViewModel = viewModel(),
    agendaVm: AgendaViewModel = viewModel()
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val servicios = serviciosVm.servicios
    val weatherState by agendaVm.weatherState.collectAsState()

    LaunchedEffect(Unit) {
        agendaVm.loadWeatherForCity("Santiago,CL")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ReparaFácil") },
                actions = {
                    IconButton(onClick = onGoPerfil) {
                        Icon(Icons.Default.Build, contentDescription = "Perfil")
                    }
                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(16.dp)
        ) {

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { -40 })
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "☀️",
                            style = MaterialTheme.typography.displayMedium
                        )

                        when {
                            weatherState.loading -> {
                                Column {
                                    Text(
                                        text = "Cargando...",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                    Text(
                                        text = "Obteniendo clima",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                            weatherState.error != null -> {
                                Column {
                                    Text(
                                        text = "--°C",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                    Text(
                                        text = "No disponible",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                            else -> {
                                Column {
                                    Text(
                                        text = weatherState.temperatura,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text = weatherState.descripcion,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(700, delayMillis = 150))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = "Atención: Puede haber retrasos por el clima.",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(700, delayMillis = 300))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            "Servicios activos",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        if (servicios.isEmpty()) {
                            Text(
                                "No tienes servicios por ahora",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            servicios.take(3).forEach {
                                Text("- ${it.titulo}")
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onGoServicios,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Ver servicios")
            }
        }
    }
}
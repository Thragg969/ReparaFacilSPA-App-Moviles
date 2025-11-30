package com.example.reparafacilspa.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel
import com.example.reparafacilspa.viewmodel.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    nav: NavController,
    authVm: AuthSharedViewModel,
    agendaVm: AgendaViewModel
) {
    val weatherState by agendaVm.weatherState.collectAsState()

    LaunchedEffect(Unit) {
        agendaVm.loadWeatherForCity("Santiago")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ReparaFácil SPA") },
                actions = {
                    IconButton(onClick = { nav.navigate("perfil") }) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil")
                    }
                    IconButton(onClick = {
                        authVm.logout()
                        nav.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Salir")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bienvenida
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            // Widget del clima
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = weatherState.ciudad.ifEmpty { "Santiago" },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            if (weatherState.loading) {
                                CircularProgressIndicator(modifier = Modifier.size(20.dp))
                            } else if (weatherState.error != null) {
                                Text(
                                    text = weatherState.error ?: "",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.error
                                )
                            } else {
                                Text(
                                    text = weatherState.descripcion,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        if (!weatherState.loading && weatherState.error == null) {
                            Text(
                                text = weatherState.temperatura,
                                style = MaterialTheme.typography.displayMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Divider()

            Text(
                text = "Accesos Rápidos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // Flujo principal: Solicitar reparación → Técnicos → Agendar
            MenuCard(
                title = "Solicitar Reparación",
                description = "Ver servicios disponibles",
                icon = Icons.Default.Build,
                onClick = { nav.navigate("servicios") }
            )

            MenuCard(
                title = "Técnicos Disponibles",
                description = "Ver y contactar técnicos",
                icon = Icons.Default.Person,
                onClick = { nav.navigate("tecnicos") }
            )

            MenuCard(
                title = "Mis Reparaciones",
                description = "Historial de servicios realizados",
                icon = Icons.Default.List,
                onClick = { nav.navigate("reparaciones") }
            )

            MenuCard(
                title = "Mis Garantías",
                description = "Ver garantías vigentes",
                icon = Icons.Default.CheckCircle,
                onClick = { nav.navigate("garantias") }
            )

            MenuCard(
                title = "Mi Agenda",
                description = "Ver eventos programados",
                icon = Icons.Default.DateRange,
                onClick = { nav.navigate("agenda") }
            )
        }
    }
}

@Composable
fun MenuCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Ir",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
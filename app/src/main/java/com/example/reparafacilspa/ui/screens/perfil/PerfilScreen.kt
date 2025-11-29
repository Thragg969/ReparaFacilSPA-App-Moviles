@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.reparafacilspa.ui.screens.perfil

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel

@Composable
fun PerfilScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit,
    authVm: AuthSharedViewModel
) {
    var showPicker by remember { mutableStateOf(false) }
    var camaraDenegada by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    val currentAvatarBitmap by remember { derivedStateOf { authVm.avatarBitmap } }
    val currentAvatarUri by remember { derivedStateOf { authVm.avatarUri } }
    val userName by remember { derivedStateOf { authVm.name } }
    val userEmail by remember { derivedStateOf { authVm.email } }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> authVm.setAvatarUri(uri?.toString()) }

    val takePhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bmp: Bitmap? -> if (bmp != null) authVm.setAvatarBitmap(bmp) }

    val requestCameraPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) takePhoto.launch(null) else camaraDenegada = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,   // <-- FIX
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { pad ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
        ) {

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { -40 })
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .border(
                                    3.dp,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    CircleShape
                                )
                                .clickable { showPicker = true },
                            contentAlignment = Alignment.Center
                        ) {
                            when {
                                currentAvatarBitmap != null ->
                                    Image(
                                        bitmap = currentAvatarBitmap!!.asImageBitmap(),
                                        contentDescription = "Avatar",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )

                                currentAvatarUri != null ->
                                    Image(
                                        painter = rememberAsyncImagePainter(currentAvatarUri),
                                        contentDescription = "Avatar",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )

                                else ->
                                    Icon(
                                        Icons.Default.Person,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(50.dp)
                                    )
                            }

                            Surface(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .size(32.dp),
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        Icons.Default.Add,
                                        contentDescription = "Cambiar foto",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = userName ?: "Usuario ReparaFácil",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        )

                        Text(
                            text = userEmail ?: "usuario@reparafacil.cl",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { 40 })
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    ProfileOptionCard(
                        icon = Icons.Default.Build,
                        title = "Mis Servicios",
                        subtitle = "Ver historial de reparaciones",
                        onClick = {}
                    )

                    ProfileOptionCard(
                        icon = Icons.Default.Settings,
                        title = "Configuración",
                        subtitle = "Ajustes de la cuenta",
                        onClick = {}
                    )

                    ProfileOptionCard(
                        icon = Icons.Default.Info,
                        title = "Centro de Ayuda",
                        subtitle = "Preguntas frecuentes",
                        onClick = {}
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { showLogoutDialog = true },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ExitToApp,   // <-- FIX
                    contentDescription = "logout",
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Cerrar Sesión",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

    if (showPicker) {
        AlertDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = {
                Button(
                    onClick = {
                        showPicker = false
                        requestCameraPermission.launch(android.Manifest.permission.CAMERA)
                    }
                ) { Text("Cámara") }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showPicker = false
                        pickImage.launch("image/*")
                    }
                ) { Text("Galería") }
            },
            title = { Text("Cambiar foto de perfil") }
        )
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        authVm.logout()
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) { Text("Cerrar Sesión") }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) { Text("Cancelar") }
            },
            title = { Text("¿Cerrar sesión?") }
        )
    }
}

@Composable
private fun ProfileOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.reparafacilspa.ui.screens.perfil

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel

@Composable
fun PerfilScreen(
    onBack: () -> Unit,
    authVm: AuthSharedViewModel
) {
    var showPicker by remember { mutableStateOf(false) }

    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        authVm.setAvatarUri(uri?.toString())
    }

    val takePhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bmp: Bitmap? ->
        authVm.setAvatarBitmap(bmp)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("<")
                    }
                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .clickable { showPicker = true },
                contentAlignment = Alignment.Center
            ) {
                when {
                    authVm.avatarBitmap != null -> {
                        Image(
                            bitmap = authVm.avatarBitmap!!,
                            contentDescription = "Avatar",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    authVm.avatarUri != null -> {
                        Image(
                            painter = rememberAsyncImagePainter(authVm.avatarUri),
                            contentDescription = "Avatar",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    else -> {
                        Text("Toca para foto")
                    }
                }
            }

            Text(authVm.name ?: "Usuario ReparaFácil")
            Text(authVm.email ?: "usuario@reparafacil.cl", style = MaterialTheme.typography.bodyMedium)
        }
    }

    if (showPicker) {
        AlertDialog(
            onDismissRequest = { showPicker = false },
            title = { Text("Seleccionar foto") },
            text = { Text("Elige desde dónde quieres la imagen") },
            confirmButton = {
                TextButton(onClick = {
                    showPicker = false
                    takePhoto.launch(null)
                }) {
                    Text("Cámara")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showPicker = false
                    pickImage.launch("image/*")
                }) {
                    Text("Galería")
                }
            }
        )
    }
}

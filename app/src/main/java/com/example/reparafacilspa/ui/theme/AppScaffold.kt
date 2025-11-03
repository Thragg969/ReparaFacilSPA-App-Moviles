package com.example.reparafacilspa.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RFScaffold(
    title: String,
    onBack: (() -> Unit)? = null,
    floatingActionButton: (@Composable (() -> Unit))? = null,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                }
            )
        },
        // Scaffold siempre espera un composable; si es null no se dibuja nada.
        floatingActionButton = { floatingActionButton?.invoke() }
    ) { inner ->
        content(Modifier.padding(inner))
    }
}

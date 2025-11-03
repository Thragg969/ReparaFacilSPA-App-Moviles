package com.example.reparafacilspa.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.reparafacilspa.ui.theme.RFScaffold

@Composable
fun LoginScreen(
    onLogin: (email: String, pass: String) -> Unit,
    onBack: (() -> Unit)? = null
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }

    RFScaffold(title = "Ingresar", onBack = onBack) { mod ->
        Column(
            modifier = mod
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            // Toggle simple para mostrar/ocultar sin íconos
            TextButton(onClick = { show = !show }, modifier = Modifier.align(Alignment.End)) {
                Text(if (show) "Ocultar" else "Mostrar")
            }

            Button(
                onClick = { onLogin(email.trim(), pass) },
                enabled = email.isNotBlank() && pass.isNotBlank(),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Ingresar")
            }
        }
    }
}

package com.example.reparafacilspa.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.reparafacilspa.core.auth.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLogin: (email: String, pass: String) -> Unit,
    onBack: (() -> Unit)? = null
) {
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var showPass by rememberSaveable { mutableStateOf(false) }

    // estado para error y loading
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    // para correr la llamada a la API
    val scope = rememberCoroutineScope()
    // repo que habla con la API del profe
    val repo = remember { AuthRepository() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ingresar") },
                navigationIcon = {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Text("<")
                        }
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    error = null
                },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = pass,
                onValueChange = {
                    pass = it
                    error = null
                },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = { showPass = !showPass },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if (showPass) "Ocultar" else "Mostrar")
            }

            if (error != null) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = {
                    if (email.isBlank() || pass.isBlank()) {
                        error = "Completa los campos"
                        return@Button
                    }

                    loading = true
                    error = null

                    scope.launch {
                        try {
                            // llamada real a la API
                            val token = repo.login(email.trim(), pass)

                            // si quieres aquí podrías hacer repo.me(token)

                            // seguimos el flujo de tu app
                            onLogin(email.trim(), pass)
                        } catch (e: Exception) {
                            // si la API no responde o credenciales malas
                            error = e.message ?: "Error al conectar con la API"
                        } finally {
                            loading = false
                        }
                    }
                },
                enabled = email.isNotBlank() && pass.isNotBlank() && !loading,
                modifier = Modifier.align(Alignment.End)
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Ingresar")
                }
            }
        }
    }
}


@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.reparafacilspa.ui.screens.login

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reparafacilspa.core.backend.AuthBackendRepository
import com.example.reparafacilspa.core.local.SessionManager
import com.example.reparafacilspa.viewmodel.AuthSharedViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLogin: (email: String, pass: String) -> Unit,
    onBack: (() -> Unit)? = null,
    onRegister: () -> Unit,
    authVm: AuthSharedViewModel
) {
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var showPass by rememberSaveable { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // 🔥 USAR BACKEND REAL
    val backendRepo = remember { AuthBackendRepository() }
    val sessionManager = remember { SessionManager(context) }

    // Animación de entrada
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { -40 })
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Logo/Icono
                    Surface(
                        modifier = Modifier.size(80.dp),
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.primary,
                        shadowElevation = 8.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Build,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "ReparaFácil",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Soluciones técnicas a domicilio",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Card de Login
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(600, delayMillis = 200)) +
                        slideInVertically(
                            initialOffsetY = { 60 },
                            animationSpec = tween(600, delayMillis = 200)
                        )
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Iniciar Sesión",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        // Campo Email
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it; error = null },
                            label = { Text("Correo electrónico") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Email,
                                    contentDescription = null
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            ),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Campo Password
                        OutlinedTextField(
                            value = pass,
                            onValueChange = { pass = it; error = null },
                            label = { Text("Contraseña") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Lock,
                                    contentDescription = null
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { showPass = !showPass }) {
                                    Icon(
                                        imageVector = if (showPass)
                                            Icons.Default.Info
                                        else
                                            Icons.Default.Info,
                                        contentDescription = if (showPass)
                                            "Ocultar contraseña"
                                        else
                                            "Mostrar contraseña"
                                    )
                                }
                            },
                            singleLine = true,
                            visualTransformation = if (showPass)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Mensaje de error
                        AnimatedVisibility(visible = error != null) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.Warning,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = error ?: "",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // 🔥 BOTÓN DE LOGIN CON BACKEND REAL
                        Button(
                            onClick = {
                                if (email.isBlank() || pass.isBlank()) {
                                    error = "Por favor completa todos los campos"
                                    return@Button
                                }

                                loading = true
                                error = null

                                scope.launch {
                                    try {
                                        // 📡 Llamada al backend real
                                        val response = backendRepo.login(
                                            email = email.trim(),
                                            password = pass
                                        )

                                        // ✅ Login exitoso
                                        if (response.success) {
                                            val userData = response.data
                                            val token = userData.access_token
                                            val user = userData.user

                                            // 💾 Guardar token en SessionManager
                                            sessionManager.setToken(token)

                                            // 👤 Guardar datos del usuario en ViewModel
                                            authVm.setUser(
                                                nombre = user.email.substringBefore("@"),
                                                correo = user.email
                                            )

                                            // ✅ Navegar a Home
                                            onLogin(email.trim(), pass)

                                        } else {
                                            error = response.message ?: "Error al iniciar sesión"
                                        }

                                    } catch (e: Exception) {
                                        // ❌ Manejo de errores mejorado
                                        error = when {
                                            e.message?.contains("401") == true ->
                                                "Credenciales incorrectas"
                                            e.message?.contains("404") == true ->
                                                "Servidor no encontrado"
                                            e.message?.contains("timeout") == true ->
                                                "Tiempo de espera agotado"
                                            e.message?.contains("Unable to resolve host") == true ->
                                                "Sin conexión a internet"
                                            e.message?.contains("Failed to connect") == true ->
                                                "No se pudo conectar al servidor"
                                            else ->
                                                e.message ?: "Error al conectar con el servidor"
                                        }
                                    } finally {
                                        loading = false
                                    }
                                }
                            },
                            enabled = !loading,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            if (loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                Text(
                                    text = "Iniciar Sesión",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }

                        // Divider
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            HorizontalDivider(modifier = Modifier.weight(1f))
                            Text(
                                text = "o",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            HorizontalDivider(modifier = Modifier.weight(1f))
                        }

                        // Botón de Registro
                        OutlinedButton(
                            onClick = onRegister,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Crear cuenta nueva",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
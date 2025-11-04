package com.example.reparafacilspa.ui.screens.login

data class LoginUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val token: String? = null
)

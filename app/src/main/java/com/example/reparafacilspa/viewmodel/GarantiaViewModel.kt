package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class GarantiaItem(
    val servicioId: Int,
    val fechaInicio: String,
    val fechaFin: String,
    val condiciones: String
)

class GarantiaViewModel : ViewModel() {
    private val _garantias = MutableStateFlow<List<GarantiaItem>>(emptyList())
    val garantias: StateFlow<List<GarantiaItem>> = _garantias

    suspend fun registrar(item: GarantiaItem) {
        _garantias.value = _garantias.value + item
    }
}

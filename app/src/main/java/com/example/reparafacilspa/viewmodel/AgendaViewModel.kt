package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AgendaItem(
    val servicioId: Int,
    val inicio: String,
    val fin: String,
    val direccion: String
)

class AgendaViewModel : ViewModel() {
    private val _agenda = MutableStateFlow<List<AgendaItem>>(emptyList())
    val agenda: StateFlow<List<AgendaItem>> = _agenda

    // si tienes algo como agendar(...)
    suspend fun agendar(item: AgendaItem) {
        _agenda.value = _agenda.value + item
    }
}


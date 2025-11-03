package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reparafacilspa.ServiceLocator
import com.example.reparafacilspa.data.model.Servicio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ServiciosViewModel : ViewModel() {
    private val repo = ServiceLocator.servicioRepository

    private val _servicios = MutableStateFlow<List<Servicio>>(emptyList())
    val servicios: StateFlow<List<Servicio>> = _servicios.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun cargar() {
        viewModelScope.launch {
            _loading.value = true
            _servicios.value = repo.listar()
            _loading.value = false
        }
    }

    fun crearServicio(titulo: String, descripcion: String) {
        viewModelScope.launch {
            _loading.value = true
            repo.crear(titulo, descripcion)
            _servicios.value = repo.listar()
            _loading.value = false
        }
    }
}

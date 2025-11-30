package com.example.reparafacilspa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.reparafacilspa.data.GlobalRepo
import com.example.reparafacilspa.data.model.Servicio

class ServiciosViewModel : ViewModel() {

    private val repo = GlobalRepo.repo

    private val _servicios = mutableStateListOf<Servicio>()
    val servicios: List<Servicio> get() = _servicios

    var lastMessage by mutableStateOf<String?>(null)
        private set

    init {
        _servicios.addAll(repo.getServicios())
    }

    fun addServicio(titulo: String, descripcion: String): Servicio {
        val nuevo = repo.createServicio(titulo, descripcion)
        _servicios.add(nuevo)
        lastMessage = "Servicio creado correctamente."
        return nuevo
    }

    fun getServicio(id: Int) = _servicios.find { it.id == id }

    fun updateServicio(servicio: Servicio) {
        repo.updateServicio(servicio)
        val i = _servicios.indexOfFirst { it.id == servicio.id }
        if (i != -1) _servicios[i] = servicio
        lastMessage = "Servicio actualizado"
    }

    fun deleteServicio(id: Int) {
        repo.deleteServicio(id)
        _servicios.removeAll { it.id == id }
        lastMessage = "Servicio eliminado"
    }

    fun clearMessage() {
        lastMessage = null
    }
}

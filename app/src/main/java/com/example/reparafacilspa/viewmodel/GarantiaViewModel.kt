package com.example.reparafacilspa.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class GarantiaUi(
    val id: Int,
    val reparacionId: Int,
    val descripcion: String,
    val fechaInicio: String,
    val fechaVencimiento: String,
    val estado: String,
    val cobertura: String
)

class GarantiaViewModel : ViewModel() {

    private val _garantias = mutableStateListOf(
        GarantiaUi(
            1,
            1,
            "Garantía de refrigerador Samsung",
            "2024-11-15",
            "2025-05-15",
            "Vigente",
            "Mano de obra y repuestos"
        ),
        GarantiaUi(
            2,
            3,
            "Garantía lavadora LG",
            "2024-11-10",
            "2025-02-10",
            "Vigente",
            "Solo mano de obra"
        )
    )

    val garantias: List<GarantiaUi> get() = _garantias

    var lastMessage by mutableStateOf<String?>(null)
        private set

    private var nextId = 3

    fun crearGarantia(
        reparacionId: Int,
        descripcion: String,
        fechaInicio: String,
        meses: Int
    ) {
        // Calcular fecha de vencimiento (simplificado)
        val año = fechaInicio.substring(0, 4).toInt()
        val mes = fechaInicio.substring(5, 7).toInt()
        val dia = fechaInicio.substring(8, 10)

        val nuevoMes = mes + meses
        val nuevoAño = if (nuevoMes > 12) año + 1 else año
        val mesFinal = if (nuevoMes > 12) nuevoMes - 12 else nuevoMes

        val fechaVencimiento = "$nuevoAño-${mesFinal.toString().padStart(2, '0')}-$dia"

        _garantias.add(
            GarantiaUi(
                id = nextId++,
                reparacionId = reparacionId,
                descripcion = descripcion,
                fechaInicio = fechaInicio,
                fechaVencimiento = fechaVencimiento,
                estado = "Vigente",
                cobertura = "Mano de obra y repuestos"
            )
        )
        lastMessage = "Garantía creada exitosamente"
    }

    fun getGarantia(id: Int): GarantiaUi? {
        return _garantias.find { it.id == id }
    }

    fun getGarantiasVigentes(): List<GarantiaUi> {
        return _garantias.filter { it.estado == "Vigente" }
    }

    fun clearMessage() {
        lastMessage = null
    }
}

package com.example.reparafacilspa.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.serviciosDataStore by preferencesDataStore("servicios_prefs")

data class ServicioDto(
    val id: Long,
    val titulo: String,
    val descripcion: String,
    val lat: Double? = null,
    val lng: Double? = null
)

class ServiciosLocal(private val context: Context) {
    private val gson = Gson()
    private val KEY = stringPreferencesKey("servicios_json")

    val servicios: Flow<List<ServicioDto>> = context.serviciosDataStore.data.map { prefs ->
        val json = prefs[KEY] ?: "[]"
        val type = object : TypeToken<List<ServicioDto>>() {}.type
        gson.fromJson<List<ServicioDto>>(json, type) ?: emptyList()
    }

    suspend fun save(lista: List<ServicioDto>) {
        val json = gson.toJson(lista)
        context.serviciosDataStore.edit { prefs ->
            prefs[KEY] = json
        }
    }
}

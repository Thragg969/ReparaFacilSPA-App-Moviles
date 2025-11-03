package com.example.reparafacilspa.core.local

import android.content.Context
import com.google.android.gms.location.LocationServices

object LocationHelper {
    /**
     * Obtiene una ubicación rápida (última conocida). Si no hay permisos o falla, devuelve nulls.
     */
    fun requestSingleUpdate(
        context: Context,
        onResult: (lat: Double?, lng: Double?) -> Unit
    ) {
        val client = LocationServices.getFusedLocationProviderClient(context)
        try {
            client.lastLocation
                .addOnSuccessListener { loc ->
                    if (loc != null) onResult(loc.latitude, loc.longitude)
                    else onResult(null, null)
                }
                .addOnFailureListener { onResult(null, null) }
        } catch (_: SecurityException) {
            onResult(null, null)
        }
    }
}

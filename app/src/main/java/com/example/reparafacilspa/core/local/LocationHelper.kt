package com.example.reparafacilspa.core.local

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class LocationHelper(private val context: Context) {

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): Pair<Double, Double>? {
        val client = LocationServices.getFusedLocationProviderClient(context)
        val loc = client.lastLocation.await() ?: return null
        return loc.latitude to loc.longitude
    }
}

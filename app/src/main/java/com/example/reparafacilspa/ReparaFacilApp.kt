package com.example.reparafacilspa

import android.app.Application

class ReparaFacilApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: ReparaFacilApp
            private set
    }
}

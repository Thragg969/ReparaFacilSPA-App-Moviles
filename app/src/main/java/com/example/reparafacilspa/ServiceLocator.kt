package com.example.reparafacilspa

import com.example.reparafacilspa.core.local.SessionManager
import com.example.reparafacilspa.data.repository.AuthRepository
import com.example.reparafacilspa.data.repository.ServicioRepository

object ServiceLocator {
    private val appCtx get() = ReparaFacilApp.instance.applicationContext

    val session by lazy { SessionManager(appCtx) }

    val authRepository by lazy { AuthRepository(session) }
    val servicioRepository by lazy { ServicioRepository() }
}

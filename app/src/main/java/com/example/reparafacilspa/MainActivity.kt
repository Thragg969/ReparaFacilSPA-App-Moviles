package com.example.reparafacilspa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reparafacilspa.ui.navigation.AppNav
import com.example.reparafacilspa.ui.theme.RFTheme   // <— usa el tema que SÍ tienes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RFTheme {            // <— OBLIGATORIO con lambda
                AppNav()
            }
        }
    }
}
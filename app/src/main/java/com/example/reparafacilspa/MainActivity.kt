package com.example.reparafacilspa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reparafacilspa.ui.navigation.AppNav
import com.example.reparafacilspa.ui.theme.ReparaFacilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReparaFacilTheme {
                AppNav()
            }
        }
    }
}

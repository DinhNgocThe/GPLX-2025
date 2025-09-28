package com.utc.driverxy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.utc.driverxy.navigation.NavRoutes
import com.utc.driverxy.presentation.theme.DriverXyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DriverXyTheme {
                NavRoutes()
            }
        }
    }
}

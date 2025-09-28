package com.utc.driverxy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.utc.driverxy.navigation.NavRoutes
import com.utc.driverxy.presentation.theme.DriverXyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            setTheme(R.style.Theme_DriverXy)
        }

        enableEdgeToEdge()
        setContent {
            DriverXyTheme {
                NavRoutes()
            }
        }
    }
}

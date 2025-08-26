package com.example.gplxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gplxapp.presentation.splash.WelcomeScreen
import com.example.gplxapp.presentation.theme.GPLXAppTheme
import com.example.gplxapp.presentation.theme.GPLXAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GPLXAppTheme {
                WelcomeScreen(
                    onLoginClick = {
                        // TODO: điều hướng sang LoginScreen
                    },
                    onRegisterClick = {
                        // TODO: điều hướng sang RegisterScreen
                    }
                )
            }
        }
    }
}

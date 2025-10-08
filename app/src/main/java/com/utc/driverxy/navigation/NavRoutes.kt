package com.utc.driverxy.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.utc.driverxy.presentation.login.LoginScreen
import com.utc.driverxy.presentation.onboarding.OnboardingScreen
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.splash.SplashScreen

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack<Destination>(Destination.Splash)

    Scaffold { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Destination.Splash> {
                    SplashScreen(
                        navigateToWelcome = {
                            backStack.replaceTop(Destination.Welcome)
                        },
                        navigateToLogin = {
                            backStack.replaceTop(Destination.Login)
                        }
                    )
                }

                entry<Destination.Welcome> {
                    WelcomeScreen(innerPadding = innerPadding) {
                        backStack.replaceTop(Destination.Onboarding)
                    }
                }

                entry<Destination.Onboarding> {
                    OnboardingScreen(innerPadding = innerPadding)
                }

                entry<Destination.Login> {
                    LoginScreen(
                        navigateToMain = {}
                    )
                }
            }
        )
    }
}

fun <T : NavKey> NavBackStack<T>.replaceTop(new: T) {
    removeLastOrNull()
    add(new)
}



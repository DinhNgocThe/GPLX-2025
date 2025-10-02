package com.utc.driverxy.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.utc.driverxy.presentation.login.LoginScreenRoute
import com.utc.driverxy.presentation.onboarding.OnboardingScreen
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.splash.SplashScreen
import androidx.navigation3.runtime.NavBackStack

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack<Destination>(Destination.Splash)

    Scaffold { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                // Splash
                entry<Destination.Splash> {
                    SplashScreen(
                        navigateToWelcome = {
                            backStack.add(Destination.Welcome)
                        }
                    )
                }

                // Welcome
                entry<Destination.Welcome> {
                    WelcomeScreen(
                        innerPadding = innerPadding,
                        navigateToLogin = { backStack.add(Destination.Login) }
                    )
                }

                // Login
                entry<Destination.Login> {
                    LoginScreenRoute(
                        onLoginSuccess = {
                            backStack.add(Destination.Onboarding)
                        },
                        onContinueWithoutLogin = {
                            backStack.add(Destination.Onboarding)
                        }
                    )
                }

                // Onboarding
                entry<Destination.Onboarding> {
                    OnboardingScreen(innerPadding = innerPadding)
                }
            }
        )
    }
}

// Extension tiện lợi
fun <T : Destination> NavBackStack<T>.replaceTop(new: T) {
    this.removeLastOrNull()
    this.add(new)
}

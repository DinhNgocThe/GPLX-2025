package com.utc.driverxy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.utc.driverxy.presentation.main.MainScreen
import com.utc.driverxy.presentation.onboarding.OnboardingScreen
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.signin.SignInScreen
import com.utc.driverxy.presentation.splash.SplashScreen
import com.utc.driverxy.utils.ext.replaceTop

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack(Destination.Splash)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Destination.Splash> {
                SplashScreen(
                    navigateToWelcome = {
                        backStack.replaceTop(Destination.Welcome)
                    },
                    navigateToMain = {
                        backStack.replaceTop(Destination.Main)
                    },
                    navigateToSignIn = {
                        backStack.replaceTop(Destination.SignIn)
                    }
                )
            }

            entry<Destination.Welcome> {
                WelcomeScreen(
                    navigateToOnboarding = { backStack.replaceTop(Destination.Onboarding) }
                )
            }

            entry<Destination.Onboarding> {
                OnboardingScreen(
                    navigateToSignIn = { backStack.replaceTop(Destination.SignIn) }
                )
            }

            entry<Destination.SignIn> {
                SignInScreen(
                    navigateToHome = { backStack.replaceTop(Destination.Main) }
                )
            }

            entry<Destination.Main> {
                MainScreen()
            }
        }
    )
}




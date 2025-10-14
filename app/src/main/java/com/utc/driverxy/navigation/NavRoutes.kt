package com.utc.driverxy.navigation

import android.graphics.Insets.add
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.onboarding.OnboardingScreen
import com.utc.driverxy.presentation.signin.SignInScreen
import com.utc.driverxy.presentation.splash.SplashScreen

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack(Destination.Splash)

    Scaffold(
        bottomBar = {
            // BottomBar layout
        }
    ) { innerPadding ->
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
                            //backStack.replaceTop(Destination.Main)
                        },
                        navigateToSignIn = {
                            backStack.replaceTop(Destination.SignIn)
                        }
                    )
                }

                entry<Destination.Welcome> {
                    WelcomeScreen(
                        innerPadding = innerPadding,
                        navigateToOnboarding = { backStack.replaceTop(Destination.Onboarding) }
                    )
                }

                entry<Destination.Onboarding> {
                    OnboardingScreen(
                        innerPadding = innerPadding,
                        navigatetoSignIn = { backStack.replaceTop(Destination.SignIn) }
                    )
                }

                entry<Destination.SignIn> {
                    SignInScreen(
                        innerPadding = innerPadding
                        //navigateToLogin = { backStack.replaceTop(Destination.Login) }
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



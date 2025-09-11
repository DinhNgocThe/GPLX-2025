package com.utc.gplx2025.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.utc.gplx2025.presentation.login.LoginScreen
import com.utc.gplx2025.presentation.signUp.SignUpScreen

@Composable
fun NavRoutes(
    isLogged: Boolean,
) {
    val firstRoute = Destination.LoginRoute // After Splash, can be LoginRoute or HomeRoute
    val backStack = rememberNavBackStack(firstRoute)

    Scaffold(
        bottomBar = {
            // BottomBar layout
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Destination.LoginRoute> {
                    LoginScreen(
                        innerPadding = innerPadding,
                        navigateToSignUp = { backStack.add(Destination.SignUpRoute) }
                    )
                }

                entry<Destination.SignUpRoute> {
                    SignUpScreen(
                        innerPadding = innerPadding,
                        navigateToLogin = { backStack.add(Destination.LoginRoute) }
                    )
                }
            }
        )
    }
}
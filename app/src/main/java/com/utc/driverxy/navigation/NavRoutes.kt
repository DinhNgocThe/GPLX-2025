package com.utc.driverxy.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.onboarding.OnboardingScreen

@Composable
fun NavRoutes() {
    val firstRoute = Destination.Welcome
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
                entry<Destination.Welcome> {
                    WelcomeScreen(
                        innerPadding = innerPadding,
                        navigateToOnboarding = { backStack.add(Destination.Onboarding) }
                    )
                }

                entry<Destination.Onboarding> {
                    OnboardingScreen(
                        innerPadding = innerPadding
                    )
                }
            }
        )
    }
}
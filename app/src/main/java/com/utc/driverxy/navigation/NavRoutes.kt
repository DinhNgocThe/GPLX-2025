package com.utc.driverxy.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.utc.driverxy.presentation.changeRank.ChangeRankScreen
import com.utc.driverxy.presentation.main.MainScreen
import com.utc.driverxy.presentation.onboarding.OnboardingScreen
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.practiceQuestion.PracticeQuestionScreen
import com.utc.driverxy.presentation.scanTrafficSigns.ScanTrafficSignsScreen
import com.utc.driverxy.presentation.signin.SignInScreen
import com.utc.driverxy.presentation.splash.SplashScreen
import com.utc.driverxy.presentation.wrongQuestion.WrongQuestionScreen
import com.utc.driverxy.utils.ext.replaceTop

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack(Destination.Splash)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
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
                    navigateToOnboarding = {
                        backStack.replaceTop(Destination.Onboarding)
                    }
                )
            }

            entry<Destination.Onboarding> {
                OnboardingScreen(
                    navigateToSignIn = {
                        backStack.replaceTop(Destination.SignIn)
                    }
                )
            }

            entry<Destination.SignIn> {
                SignInScreen(
                    navigateToHome = {
                        backStack.replaceTop(Destination.Main)
                    }
                )
            }

            entry<Destination.Main> {
                MainScreen(
                    navigateToTrafficSigns = {
                        backStack.add(Destination.ScanTrafficSigns)
                    },
                    navigateToChangeRank = {
                        backStack.add(Destination.ChangeRank)
                    },
                    navigateToPracticeQuestion = {
                        backStack.add(Destination.PracticeQuestion(it))
                    },
                    navigateToWrongQuestion = {
                        backStack.add(Destination.WrongQuestion)
                    }
                )
            }

            entry<Destination.ScanTrafficSigns> {
                ScanTrafficSignsScreen(
                    onNavigateBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<Destination.ChangeRank> {
                ChangeRankScreen(
                    navigateBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<Destination.PracticeQuestion> {
                PracticeQuestionScreen(
                    topicId = it.topicId,
                    navigateBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<Destination.WrongQuestion> {
                WrongQuestionScreen(
                    navigateBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}




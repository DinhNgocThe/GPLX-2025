package com.utc.driverxy.presentation.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.utc.driverxy.presentation.exam.ExamScreen
import com.utc.driverxy.presentation.home.HomeScreen
import com.utc.driverxy.presentation.main.components.BottomNavBar
import com.utc.driverxy.presentation.main.model.MainTab
import com.utc.driverxy.presentation.practice.PracticeScreen
import com.utc.driverxy.utils.CameraPermissionRequestOnce
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    navigateToTrafficSigns: () -> Unit,
    navigateToChangeRank: () -> Unit,
    navigateToPracticeQuestion: (String) -> Unit,
    viewModel: MainViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    CameraPermissionRequestOnce { onResult ->

    }

    MainScreenContent(
        viewState = viewState,
        onTabClick = {
            viewModel.processIntent(MainIntent.NavigateToTab(it))
        },
        navigateToTrafficSigns = {
            navigateToTrafficSigns()
        },
        navigateToChangeRank = {
            navigateToChangeRank()
        },
        navigateToPracticeQuestion = {
            navigateToPracticeQuestion(it)
        }
    )
}

@Composable
fun MainScreenContent(
    viewState: MainState,
    onTabClick: (MainTab) -> Unit,
    navigateToTrafficSigns: () -> Unit,
    navigateToChangeRank: () -> Unit,
    navigateToPracticeQuestion: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AnimatedContent(
            targetState = viewState.currentTab,
            transitionSpec = {
                (fadeIn(animationSpec = tween(150)) +
                        scaleIn(initialScale = 0.96f, animationSpec = tween(150)))
                    .togetherWith(fadeOut(animationSpec = tween(150)))
            },
            label = "main_content",
        ) { page ->
            key(page) {
                when (page) {
                    MainTab.HOME -> {
                        HomeScreen(
                            navigateToScanTrafficSigns = navigateToTrafficSigns,
                            navigateToChangeRank = navigateToChangeRank
                        )
                    }
                    MainTab.PRACTICE -> {
                        PracticeScreen(
                            navigateToPracticeQuestion = {
                                navigateToPracticeQuestion(it)
                            }
                        )
                    }
                    MainTab.EXAM -> ExamScreen()
                }
            }
        }

        // Bottom nav bar
        BottomNavBar(
            tabSelected = viewState.currentTab,
            onTabClick = onTabClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreenContent(
        MainState(),
        onTabClick = {},
        navigateToTrafficSigns = {},
        navigateToChangeRank = {},
        navigateToPracticeQuestion = {},
    )
}
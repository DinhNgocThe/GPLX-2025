package com.utc.driverxy.presentation.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.utc.driverxy.presentation.theme.DriverXyColors

@Composable
fun DriverXyTheme(
    statusBarDarkIcons: Boolean = true,
    navigationBarDarkIcons: Boolean = true,
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window
    val insetsController = remember(window) {
        window?.let { WindowCompat.getInsetsController(it, view) }
    }

    SideEffect {
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)

            insetsController?.isAppearanceLightStatusBars = statusBarDarkIcons
            insetsController?.isAppearanceLightNavigationBars = navigationBarDarkIcons

            it.navigationBarColor = navigationBarColor.toArgb()
            it.statusBarColor = statusBarColor.toArgb()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .systemBarsPadding()
    ) {
        content()
    }
}
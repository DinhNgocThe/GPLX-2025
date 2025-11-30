package com.utc.driverxy.presentation.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.utc.driverxy.presentation.theme.DriverXyColors

@Composable
fun ExamScreen(

) {
    ExamScreenContent()
}

@Composable
fun ExamScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.BackGround.BackgroundPrimary)
    ) {

    }
}
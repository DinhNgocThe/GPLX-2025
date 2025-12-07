package com.utc.driverxy.presentation.practice

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.presentation.practice.component.PracticeCard
import com.utc.driverxy.presentation.theme.DriverXyColors

@Composable
fun PracticeScreen(

) {
    PracticeScreenContent()
}

@Composable
fun PracticeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.White)
            .statusBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(8) {
            PracticeCard(
                progress = 0.25f,
                title = "Tất cả các câu hỏi",
                onStartClick = {},
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview
@Composable
private fun PracticeScreenPreview() {
    PracticeScreenContent()
}
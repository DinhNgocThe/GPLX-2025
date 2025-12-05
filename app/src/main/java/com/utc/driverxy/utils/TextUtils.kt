package com.utc.driverxy.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import kotlinx.coroutines.delay

@Composable
fun TypingTextColumn(
    fields: List<Pair<String, String>>,
    typingDelay: Long = 30L
) {
    val displayedTexts = remember { mutableStateListOf<Pair<String, String>>() }

    LaunchedEffect(fields) {
        displayedTexts.clear()
        for ((label, value) in fields) {
            if (value.isNotEmpty()) {
                var currentText = ""
                displayedTexts.add(label to "") // tạo dòng mới
                val lineIndex = displayedTexts.lastIndex
                for (char in value) {
                    currentText += char
                    displayedTexts[lineIndex] = label to currentText
                    delay(typingDelay)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(DriverXyShapes.medium)
            .background(DriverXyColors.Primary.Primary.copy(0.1f))
            .padding(16.dp)
            .animateContentSize()
    ) {
        displayedTexts.forEach { (label, value) ->
            Text(
                text = "$label:",
                style = DriverXyTypography.Title.Medium.Bold,
                color = DriverXyColors.Primary.Primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "- $value",
                style = DriverXyTypography.Title.Medium.Bold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}



package com.utc.driverxy.presentation.components.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieView(
    lottieResId: Int,
    modifier: Modifier = Modifier,
    isLooping: Boolean = true,
    isPlaying: Boolean = true,
    restartOnPlay: Boolean = false,
    speed: Float = 1f
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResId))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = if (isLooping) LottieConstants.IterateForever else 1,
        speed = speed,
        isPlaying = isPlaying,
        restartOnPlay = restartOnPlay,
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

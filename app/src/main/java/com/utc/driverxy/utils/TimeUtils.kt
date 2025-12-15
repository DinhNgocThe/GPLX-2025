package com.utc.driverxy.utils

import kotlinx.coroutines.delay

fun minutesToMillis(minutes: Int): Long {
    return minutes * 60_000L
}

suspend fun countDownTimer(
    totalTimeMillis: Long,
    onTick: (String) -> Unit,
    onFinish: () -> Unit
) {
    var remainingTime = totalTimeMillis

    while (remainingTime > 0) {
        val minutes = (remainingTime / 1000) / 60
        val seconds = (remainingTime / 1000) % 60

        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        onTick(timeFormatted)

        delay(1000L)
        remainingTime -= 1000L
    }

    onTick("00:00")
    onFinish()
}
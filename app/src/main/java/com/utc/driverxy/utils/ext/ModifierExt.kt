package com.utc.driverxy.utils.ext

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

@SuppressLint("SuspiciousModifierThen")
fun Modifier.rawClickable(onClick: () -> Unit): Modifier =
    this.clickable(
        indication = null,
        interactionSource = MutableInteractionSource()
    ) {
        onClick()
    }
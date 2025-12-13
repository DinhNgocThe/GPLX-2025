package com.utc.driverxy.utils.ext

import android.annotation.SuppressLint
import android.os.SystemClock
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

@SuppressLint("SuspiciousModifierThen")
fun Modifier.rawClickable(onClick: () -> Unit): Modifier =
    this.clickable(
        indication = null,
        interactionSource = MutableInteractionSource()
    ) {
        onClick()
    }

@Composable
fun Modifier.safeClickable(
    debounceTime: Long = 800L,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = null,
    onClick: () -> Unit
): Modifier = composed {
    val currentOnClick by rememberUpdatedState(onClick)
    var lastClickTime by remember { mutableLongStateOf(0L) }

    clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        interactionSource = interactionSource,
        indication = indication,
        onClick = {
            val now = SystemClock.elapsedRealtime()
            if (now - lastClickTime >= debounceTime) {
                currentOnClick()
                lastClickTime = now
            }
        }
    )
}
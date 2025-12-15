package com.utc.driverxy.utils.ext

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun <T : NavKey> NavBackStack<T>.replaceTop(new: T) {
    removeLastOrNull()
    add(new)
}




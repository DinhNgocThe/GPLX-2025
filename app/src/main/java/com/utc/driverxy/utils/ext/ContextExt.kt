package com.utc.driverxy.utils.ext

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openWebUrlSafely(url: String) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

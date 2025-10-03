package com.utc.driverxy.domain.provider

import android.content.Context

interface ContextProvider {
    fun getCurrentContext(): Context
    fun setCurrentContext(context: Context)
}
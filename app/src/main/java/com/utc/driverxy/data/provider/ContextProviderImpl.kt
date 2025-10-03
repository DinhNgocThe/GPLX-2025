package com.utc.driverxy.data.provider

import android.content.Context
import com.utc.driverxy.domain.provider.ContextProvider

class ContextProviderImpl : ContextProvider {
    private var currentContext: Context? = null

    override fun getCurrentContext(): Context {
        return currentContext
            ?: throw IllegalStateException("Context not set. Call setCurrentContext() first.")
    }

    override fun setCurrentContext(context: Context) {
        currentContext = context
    }
}
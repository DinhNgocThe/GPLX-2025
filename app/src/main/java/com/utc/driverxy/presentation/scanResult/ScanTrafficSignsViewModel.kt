package com.utc.driverxy.presentation.scanResult

import android.graphics.Bitmap
import com.utc.driverxy.base.BaseMviViewModel

class ScanResultViewModel(
) : BaseMviViewModel<ScanResultIntent, ScanResultState, ScanResultEvent>() {
    override fun initState(): ScanResultState {
        return ScanResultState()
    }

    override fun processIntent(intent: ScanResultIntent) {

    }
}
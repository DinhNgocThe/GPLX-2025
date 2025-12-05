package com.utc.driverxy.presentation.scanTrafficSigns

import android.net.Uri
import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.ScanTrafficSignsResult
import com.utc.driverxy.presentation.scanTrafficSigns.model.ScanState

data class ScanTrafficSignsState(
    val isImageUploading: Boolean = false,
    val imagePath: Uri? = null,
    val isShowCamera: Boolean = false,
    val scanState: ScanState = ScanState.IDLE,
    val isGenerating: Boolean = false,
    val scanResult: ScanTrafficSignsResult? = null
) : MviViewState

sealed class ScanTrafficSignsIntent : MviIntent {
    data class OnScanStateChange(val scanState: ScanState) : ScanTrafficSignsIntent()
    data class OnPhotoTaken(val uri: Uri) : ScanTrafficSignsIntent()
    data class UpdateImagePath(val imagePath: Uri) : ScanTrafficSignsIntent()
    data object OnContinueClick : ScanTrafficSignsIntent()
}

sealed class ScanTrafficSignsEvent: MviSingleEvent {

}
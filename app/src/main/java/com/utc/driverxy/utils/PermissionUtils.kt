package com.utc.driverxy.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionRequestOnce(
    onResult: (granted: Boolean) -> Unit
) {
    val permissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    var requested by remember { mutableStateOf(false) }

    LaunchedEffect(permissionState, requested) {
        if (!requested) {
            requested = true
            permissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(permissionState.status) {
        onResult(permissionState.status.isGranted)
    }
}
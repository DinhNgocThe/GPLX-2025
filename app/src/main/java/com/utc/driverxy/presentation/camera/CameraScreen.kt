package com.utc.driverxy.presentation.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import java.io.File

@Composable
fun CameraScreen(
    onPhotoTaken: (Bitmap) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel: CameraViewModel = koinViewModel()
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.cameraSelector) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    Box(modifier = modifier) {
        // Camera preview full screen
        surfaceRequest?.let { request ->
            CameraXViewfinder(
                surfaceRequest = request,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Overlay UI: buttons capture & close
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { onClose() }) {
                    Text("Close")
                }

                Button(onClick = {
                    viewModel.takePhoto(
                        saveDirectory = context.cacheDir,
                        onImageSaved = { uri ->
                            val bitmap = BitmapFactory.decodeFile(File(uri.path!!).absolutePath)
                            bitmap?.let { onPhotoTaken(it) } // tránh null
                            onClose()
                        },
                        onError = { it.printStackTrace() }
                    )
                }) {
                    Text("Capture")
                }
            }
        }
    }
}






package com.utc.driverxy.presentation.camera

import android.net.Uri
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import com.utc.driverxy.utils.ext.rawClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CameraScreen(
    onPhotoTaken: (Uri) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel: CameraViewModel = koinViewModel()
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(viewModel.cameraSelector) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
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
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(0.9f))
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(bottom = 12.dp, end = 20.dp)
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = stringResource(R.string.camera),
                    style = DriverXyTypography.Title.Large.Bold,
                    color = DriverXyColors.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 8.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    tint = DriverXyColors.White,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.BottomEnd)
                        .rawClickable {
                            onClose()
                        }
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_take_photo),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(64.dp)
                    .align(Alignment.BottomCenter)
                    .rawClickable {
                        viewModel.takePhotoToGallery(
                            context = context,
                            onImageSaved = { uri ->
                                onPhotoTaken(uri)
                            },
                            onError = {

                            }
                        )
                    }
            )
        }
    }
}






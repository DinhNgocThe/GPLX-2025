package com.utc.driverxy.presentation.camera

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.impl.utils.executor.CameraXExecutors
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CameraViewModel : ViewModel() {

    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    private val _cameraSelector = mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA)
    val cameraSelector get() = _cameraSelector.value

    private var imageCapture: ImageCapture? = null

    fun switchCamera() {
        _cameraSelector.value = if (_cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA)
            CameraSelector.DEFAULT_FRONT_CAMERA
        else
            CameraSelector.DEFAULT_BACK_CAMERA
    }

    suspend fun bindToCamera(context: Context, lifecycleOwner: LifecycleOwner) {
        val cameraProvider = ProcessCameraProvider.awaitInstance(context)
        cameraProvider.unbindAll()

        val preview = Preview.Builder().build().also { p ->
            p.setSurfaceProvider { request -> _surfaceRequest.value = request }
        }

        imageCapture = ImageCapture.Builder().build()

        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            _cameraSelector.value,
            preview,
            imageCapture
        )
    }

    fun takePhotoToGallery(
        context: Context,
        onImageSaved: (Uri) -> Unit,
        onError: (ImageCaptureException) -> Unit
    ) {
        val capture = imageCapture ?: return
        val filename = "IMG_${System.currentTimeMillis()}.jpg"

        // Metadata MediaStore
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/DriverXy") // Thư mục
        }

        // Save by MediaStore
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        capture.takePicture(
            outputOptions,
            CameraXExecutors.mainThreadExecutor(),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri
                    if (savedUri != null) {
                        onImageSaved(savedUri)
                    } else {
                        onError(
                            ImageCaptureException(
                                ImageCapture.ERROR_UNKNOWN,
                                "URI null",
                                null
                            )
                        )
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    onError(exception)
                }
            }
        )
    }
}

package com.utc.driverxy.presentation.scanTrafficSigns

import android.app.Application
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.ai.type.content
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.ai.FirebaseAiClient
import com.utc.driverxy.data.ai.FirebaseAiPromptBuilder
import com.utc.driverxy.domain.model.ScanTrafficSignsResult
import com.utc.driverxy.presentation.scanTrafficSigns.model.ScanState
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ScanTrafficSignsViewModel(
    private val context: Application
) : BaseMviViewModel<ScanTrafficSignsIntent, ScanTrafficSignsState, ScanTrafficSignsEvent>() {
    override fun initState(): ScanTrafficSignsState {
        return ScanTrafficSignsState()
    }

    override fun processIntent(intent: ScanTrafficSignsIntent) {
        when (intent) {
            is ScanTrafficSignsIntent.OnScanStateChange -> {
                updateState { copy(scanState = intent.scanState) }
            }

            is ScanTrafficSignsIntent.OnPhotoTaken -> {
                updateState {
                    copy(
                        imagePath = intent.uri,
                        scanState = ScanState.GENERATE,
                        isGenerating = true
                    )
                }
                handleGenerateImage()
            }

            is ScanTrafficSignsIntent.UpdateImagePath -> {
                updateState { copy(imagePath = intent.imagePath) }
            }

            ScanTrafficSignsIntent.OnContinueClick -> {
                handleGenerateImage()
                updateState {
                    copy(
                        isGenerating = true,
                        scanState = ScanState.GENERATE
                    )
                }
            }
        }
    }

    private fun handleGenerateImage() {
        val uri = viewState.value.imagePath ?: return

        val bitmap = context.contentResolver.openInputStream(uri)?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        } ?: return


        viewModelScope.launch {
            val textPrompt = content { text(
                FirebaseAiPromptBuilder.buildTrafficSignPrompt()
            ) }
            val bitmapPrompt = content { image(bitmap) }
            val response = FirebaseAiClient.model.generateContent(listOf(textPrompt, bitmapPrompt))
            val json = response.text
                ?.replaceFirst("```json", "")
                ?.replaceFirst("```", "")
                ?.trim()
                ?.takeIf { it.isNotEmpty() }

            json?.let { rawJson ->
                try {
                    val scanTrafficResult = Json.decodeFromString<ScanTrafficSignsResult>(rawJson)
                    updateState { copy(scanResult = scanTrafficResult) }
                } catch (e: Exception) {
                    Log.e("THEDN", "Failed to parse ScanTrafficSignsResult", e)
                }
            }

            updateState { copy(isGenerating = false) }
        }
    }
}
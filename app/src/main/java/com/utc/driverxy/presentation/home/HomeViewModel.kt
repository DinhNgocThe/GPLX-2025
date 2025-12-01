package com.utc.driverxy.presentation.home

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.ai.type.content
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.ai.FirebaseAiClient
import com.utc.driverxy.data.ai.FirebaseAiPromptBuilder
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.presentation.home.model.CantMiss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager
) : BaseMviViewModel<HomeIntent, HomeState, HomeEvent>() {
    override fun initState(): HomeState {
        return HomeState()
    }

    init {
        getUserInfo()
        getCurrentRank()
    }

    private fun getCurrentRank() {
        viewModelScope.launch {
            dataStoreManager.getCurrentRank().collect {
                updateState {
                    copy(
                        currentRank = it
                    )
                }
            }
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val user = dataStoreManager.getUserInfo().firstOrNull()
            user?.let {
                updateState {
                    copy(
                        user = user
                    )
                }
            }
        }
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnCantMissClick -> {
                handleOnCantMissClick(intent.option)
            }

            HomeIntent.OnCloseCamera -> {
                updateState { copy(isShowCamera = false) }
            }

            is HomeIntent.OnPhotoTaken -> {
                handleOnPhotoTaken(intent.bitmap)
            }
        }
    }

    private fun handleOnPhotoTaken(bitmap: Bitmap) {
        updateState {
            copy(
                isShowCamera = false
            )
        }
        Log.d("hahahahahahaha", bitmap.toString())
        viewModelScope.launch {
            val textPrompt = content { text(
                FirebaseAiPromptBuilder.buildTrafficSignPrompt()
            ) }
            val bitmapPrompt = content { image(bitmap) }
            val response = FirebaseAiClient.model.generateContent(listOf(textPrompt, bitmapPrompt))
            val textResult = response.text
            Log.d("hahahahahahaha", textResult ?: "")
        }
    }

    private fun handleOnCantMissClick(option: CantMiss) {
        when (option) {
            CantMiss.SCAN_TRAFFIC -> {
                updateState { copy(isShowCamera = true) }
            }

            CantMiss.WRONG_SENTENCE -> {

            }

            CantMiss.TIPS -> {

            }

            CantMiss.NOTED -> {

            }
        }
    }
}
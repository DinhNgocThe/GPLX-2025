package com.utc.driverxy.presentation.scanResult

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScanResultScreen(
    onNavigateBack: () -> Unit,
    viewModel: ScanResultViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ScanResultContent(
        viewState = viewState
    )
}

@Composable
fun ScanResultContent(
    viewState: ScanResultState
) {

}

@Preview
@Composable
private fun ScanResultPreview() {
    ScanResultContent(
        viewState = ScanResultState()
    )
}



package com.utc.driverxy.presentation.scanTrafficSigns

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.domain.model.ScanTrafficSignsResult
import com.utc.driverxy.presentation.camera.CameraScreen
import com.utc.driverxy.presentation.components.button.DriverXyButton
import com.utc.driverxy.presentation.components.view.DriverXyTopBar
import com.utc.driverxy.presentation.components.view.LottieView
import com.utc.driverxy.presentation.components.view.UploadImageSection
import com.utc.driverxy.presentation.scanTrafficSigns.model.ScanState
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import com.utc.driverxy.utils.TypingTextColumn
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScanTrafficSignsScreen(
    onNavigateBack: () -> Unit,
    viewModel: ScanTrafficSignsViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.processIntent(ScanTrafficSignsIntent.UpdateImagePath(uri))
        }
    }

    ScanTrafficSignsContent(
        viewState = viewState,
        onNavigateBack = {
            onNavigateBack()
        },
        onUploadImageClick = {
            launcher.launch("image/*")
        },
        onScanStateChange = {
            viewModel.processIntent(ScanTrafficSignsIntent.OnScanStateChange(it))
        },
        onContinueClick = {
            viewModel.processIntent(ScanTrafficSignsIntent.OnContinueClick)
        },
        onPhotoTaken = { uri ->
            viewModel.processIntent(ScanTrafficSignsIntent.OnPhotoTaken(uri))
        }
    )
}

@Composable
fun ScanTrafficSignsContent(
    viewState: ScanTrafficSignsState,
    onNavigateBack: () -> Unit,
    onUploadImageClick: () -> Unit,
    onScanStateChange: (ScanState) -> Unit,
    onContinueClick: () -> Unit,
    onPhotoTaken: (Uri) -> Unit
) {
    when (viewState.scanState) {
        ScanState.SHOW_CAMERA -> {
            CameraScreen(
                onPhotoTaken = { uri ->
                    onPhotoTaken(uri)
                },
                onClose = {
                    onScanStateChange(ScanState.IDLE)
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        ScanState.GENERATE -> {
            ScanGenerate(
                imagePath = viewState.imagePath,
                scanResult = viewState.scanResult,
                isGenerating = viewState.isGenerating,
                onNavigateBack = {
                    onNavigateBack()
                }
            )
        }

        ScanState.IDLE -> {
            UploadScanTrafficSigns(
                imagePath = viewState.imagePath,
                onNavigateBack = {
                    onNavigateBack()
                },
                onUploadImageClick = {
                    onUploadImageClick()
                },
                onScanStateChange = {
                    onScanStateChange(it)
                },
                onContinueClick = {
                    onContinueClick()
                }
            )
        }
    }
}

@Composable
fun UploadScanTrafficSigns(
    imagePath: Uri?,
    onNavigateBack: () -> Unit,
    onUploadImageClick: () -> Unit,
    onScanStateChange: (ScanState) -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        DriverXyTopBar(
            leadingIconRes = R.drawable.ic_arrow_left,
            title = stringResource(R.string.scan_traffic_signs),
            onLeadingClick = onNavigateBack
        )

        Text(
            text = stringResource(R.string.scan_traffic_screen_title),
            style = DriverXyTypography.Headline.Small.Bold,
            color = DriverXyColors.Text.TextPrimary,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = stringResource(R.string.scan_traffic_screen_description),
            style = DriverXyTypography.Body.Medium,
            color = DriverXyColors.Text.TextPrimary
        )

        UploadImageSection(
            imagePath = imagePath,
            modifier = Modifier
                .padding(top = 32.dp)
                .height(200.dp),
            onUploadImageClick = onUploadImageClick
        )

        if (imagePath == null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = DriverXyColors.Neutral.Neutral08
                )

                Text(
                    text = stringResource(R.string.or),
                    style = DriverXyTypography.Body.Medium,
                    color = DriverXyColors.Text.TextTertiary,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = DriverXyColors.Neutral.Neutral08
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 28.dp)
                    .clip(DriverXyShapes.extraLarge)
                    .fillMaxWidth()
                    .background(DriverXyColors.Primary.Primary.copy(0.2f))
                    .height(56.dp)
                    .clickable {
                        onScanStateChange(ScanState.SHOW_CAMERA)
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_camera_simple),
                        contentDescription = null,
                        tint = DriverXyColors.Primary.Primary,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = stringResource(R.string.open_camera),
                        style = DriverXyTypography.Title.Medium.Bold,
                        color = DriverXyColors.Primary.Primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (imagePath != null) {
            DriverXyButton(
                onClick = {
                    onContinueClick()
                },
                text = stringResource(R.string.button_continue),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .height(56.dp)
            )
        }
    }
}

@Composable
fun ScanGenerate(
    imagePath: Uri?,
    scanResult: ScanTrafficSignsResult?,
    isGenerating: Boolean,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 40.dp)
    ) {
        DriverXyTopBar(
            leadingIconRes = R.drawable.ic_arrow_left,
            title = stringResource(R.string.scan_traffic_signs),
            onLeadingClick = onNavigateBack
        )

        UploadImageSection(
            imagePath = imagePath,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 24.dp)
                .height(200.dp),
            onUploadImageClick = {}
        )

        if (isGenerating) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LottieView(
                    lottieResId = R.raw.anim_generating,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(32.dp)
                )

                Text(
                    text = stringResource(R.string.analyzing),
                    style = DriverXyTypography.Title.Medium.SemiBold,
                    color = DriverXyColors.Primary.Primary
                )
            }
        }

        scanResult?.let {
            if (scanResult.hasTrafficSigns) {
                val fields = listOf(
                    stringResource(R.string.sign_code) to scanResult.signCode,
                    stringResource(R.string.sign_name) to scanResult.signName,
                    stringResource(R.string.sign_group) to scanResult.signGroup,
                    stringResource(R.string.meaning) to scanResult.meaning,
                    stringResource(R.string.applicable_cases) to scanResult.applicableCases,
                    stringResource(R.string.notes) to scanResult.notes
                )

                TypingTextColumn(fields = fields)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(DriverXyShapes.medium)
                        .background(DriverXyColors.Primary.Primary.copy(0.1f))
                        .padding(16.dp)
                        .animateContentSize()
                ) {
                    Text(
                        text = stringResource(R.string.scan_traffic_signs_error),
                        style = DriverXyTypography.Title.Medium.Bold,
                        color = DriverXyColors.Red
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScanTrafficSignsPreview() {
    ScanTrafficSignsContent(
        viewState = ScanTrafficSignsState(),
        onNavigateBack = {},
        onUploadImageClick = {},
        onScanStateChange = {},
        onContinueClick = {},
        onPhotoTaken = {},
    )
}



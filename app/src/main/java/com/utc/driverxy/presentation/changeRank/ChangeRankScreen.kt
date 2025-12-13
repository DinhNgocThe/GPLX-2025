package com.utc.driverxy.presentation.changeRank

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.presentation.changeRank.component.RankCard
import com.utc.driverxy.presentation.components.view.LottieView
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangeRankScreen(
    navigateBack: () -> Unit,
    viewModel: ChangeRankViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.singleEvent.collect { event ->
            when (event) {
                ChangeRankEvent.ChangeRankError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.change_rank_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ChangeRankEvent.NavigateBack -> {
                    navigateBack()
                }
            }
        }
    }

    ChangeRankScreenContent(
        viewState = viewState,
        onChangeRank = {
            viewModel.processIntent(ChangeRankIntent.OnChangeRank(it))
        }
    )
}

@Composable
fun ChangeRankScreenContent(
    viewState: ChangeRankState,
    onChangeRank: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DriverXyColors.BackGround.BackgroundPrimary)
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.change_rank),
                style = DriverXyTypography.Headline.Medium.Bold,
                color = DriverXyColors.Primary.Primary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            Text(
                text = stringResource(R.string.motorbike),
                style = DriverXyTypography.Title.Large.Bold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp)
            )

            viewState.motorRanks.forEachIndexed { index, rank ->
                RankCard(
                    displayName = viewState.motorRanks[index].displayName,
                    description = viewState.motorRanks[index].description,
                    backgroundColor = DriverXyColors.ListColors.list[index % 5],
                    onClick = {
                        onChangeRank(rank.id)
                    },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            Text(
                text = stringResource(R.string.car),
                style = DriverXyTypography.Title.Large.Bold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 16.dp)
            )

            viewState.carRanks.forEachIndexed { index, rank ->
                RankCard(
                    displayName = viewState.carRanks[index].displayName,
                    description = viewState.carRanks[index].description,
                    backgroundColor = DriverXyColors.ListColors.list[(index + 4) % 5],
                    onClick = {
                        onChangeRank(rank.id)
                    },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            Spacer(Modifier.height(100.dp))
        }

        if (viewState.isLoading) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                LottieView(
                    lottieResId = R.raw.anim_loading_white,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangeRankScreenPreview() {
    ChangeRankScreenContent(
        viewState = ChangeRankState(),
        onChangeRank = {},
    )
}
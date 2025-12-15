package com.utc.driverxy.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.domain.usecase.question.GetWrongQuestion
import com.utc.driverxy.presentation.home.components.CantMissCard
import com.utc.driverxy.presentation.home.components.HomeCard
import com.utc.driverxy.presentation.home.components.HomeProgressCard
import com.utc.driverxy.presentation.home.model.CantMiss
import com.utc.driverxy.presentation.home.model.HomeProgress
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateToScanTrafficSigns: () -> Unit,
    navigateToChangeRank: () -> Unit,
    navigateToWrongQuestion: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.singleEvent.collect { event ->
            when (event) {
                HomeEvent.NavigateToScanTrafficSigns -> {
                    navigateToScanTrafficSigns()
                }

                HomeEvent.NavigateToWrongQuestion -> {
                    navigateToWrongQuestion()
                }
            }
        }
    }

    HomeScreenContent(
        viewState = viewState,
        onCantMissClick = {
            viewModel.processIntent(HomeIntent.OnCantMissClick(it))
        },
        onChangeRank = {
            navigateToChangeRank()
        }
    )
}

@Composable
fun HomeScreenContent(
    viewState: HomeState,
    onCantMissClick: (CantMiss) -> Unit,
    onChangeRank: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.White)
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        HomeCard(
            photoUrl = viewState.user?.photoUrl ?: "",
            userName = viewState.user?.name ?: "Unknown",
            rank = viewState.currentRank?.displayName ?: "Unknown",
            onChangeRank = onChangeRank,
            modifier = Modifier.padding(top = 28.dp)
        )

        Text(
            text = stringResource(R.string.cant_miss),
            style = DriverXyTypography.Title.Large.Bold,
            color = DriverXyColors.Text.TextPrimary,
            modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CantMiss.entries.forEachIndexed { index, cantMiss ->
                CantMissCard(
                    colors = DriverXyColors.Gradient.CantMiss[index],
                    title = stringResource(cantMiss.title),
                    subtitle = stringResource(cantMiss.subTitle),
                    icon = cantMiss.icon,
                    onClick = {
                        onCantMissClick(cantMiss)
                    }
                )
            }
        }

        Text(
            text = stringResource(R.string.review_progress),
            style = DriverXyTypography.Title.Large.Bold,
            color = DriverXyColors.Text.TextPrimary,
            modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
        )

        HomeProgress.entries.forEachIndexed { index, homeProgress ->
            val progress = when (homeProgress) {
                HomeProgress.CRITICAL_QUESTION -> viewState.criticalProgress
                HomeProgress.TRAFFIC_SIGNS -> viewState.trafficSignsProgress
                HomeProgress.DRIVING_SCENARIO -> viewState.saHinhProgress
                HomeProgress.EXAM -> 0 to 1
            }

            HomeProgressCard(
                icon = homeProgress.icon,
                title = stringResource(homeProgress.title),
                completed = progress.first,
                total = progress.second,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val viewState = HomeState(
        user = User(
            id = "",
            name = "Trần Hải Đăng",
            photoUrl = "",
            email = "",
            rankId = "ranka1"
        )
    )
    HomeScreenContent(
        viewState = viewState,
        onCantMissClick = {},
        onChangeRank = {}
    )
}


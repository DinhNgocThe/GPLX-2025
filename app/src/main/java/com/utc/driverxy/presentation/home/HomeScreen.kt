package com.utc.driverxy.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.presentation.home.components.HomeCard
import com.utc.driverxy.presentation.theme.DriverXyColors
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    HomeScreenContent(
        viewState = viewState
    )
}

@Composable
fun HomeScreenContent(
    viewState: HomeState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(DriverXyColors.White)
    ) {
        HomeCard(
            photoUrl = viewState.user?.photoUrl ?: "",
            userName = viewState.user?.name ?: "Unknown",
            rank = viewState.currentRank?.displayName ?: "Unknown",
            modifier = Modifier
                .padding(top = 28.dp, start = 16.dp, end = 16.dp)
        )
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
            email = ""
        )
    )
    HomeScreenContent(
        viewState = viewState
    )
}


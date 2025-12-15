package com.utc.driverxy.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.utc.driverxy.presentation.components.view.DriverXyTopBar
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel
import kotlin.math.log

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
    logout: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.singleEvent.collect { event ->
            when (event) {
                SettingsEvent.LogOut -> {
                    logout()
                }
            }
        }
    }

    SettingsScreenContent(
        viewState = viewState,
        navigateBack = navigateBack,
        logout = {
            viewModel.processIntent(SettingsIntent.Logout)
        },
        deleteAccount = {
            viewModel.processIntent(SettingsIntent.DeleteAccount)
        }
    )
}

@Composable
fun SettingsScreenContent(
    viewState: SettingsState,
    navigateBack: () -> Unit,
    logout: () -> Unit,
    deleteAccount: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.White)
            .statusBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DriverXyTopBar(
            leadingIconRes = R.drawable.ic_arrow_left,
            title = stringResource(R.string.settings),
            onLeadingClick = {
                navigateBack()
            }
        )

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(DriverXyShapes.medium)
                .clickable {
                    logout()
                }
                .height(64.dp)
                .border(
                    width = 2.dp,
                    color = DriverXyColors.Neutral.Neutral08,
                    shape = DriverXyShapes.medium
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_logout),
                contentDescription = null,
                tint = DriverXyColors.Primary.Primary,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(28.dp)
            )

            Text(
                text = stringResource(R.string.logout),
                style = DriverXyTypography.Title.Medium.SemiBold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = DriverXyColors.Black,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(DriverXyShapes.medium)
                .clickable {
                    deleteAccount()
                }
                .height(64.dp)
                .border(
                    width = 2.dp,
                    color = DriverXyColors.Border.Wrong,
                    shape = DriverXyShapes.medium
                )
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_dangerous),
                contentDescription = null,
                tint = DriverXyColors.Border.Wrong,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(28.dp)
            )

            Text(
                text = stringResource(R.string.delete_account),
                style = DriverXyTypography.Title.Medium.SemiBold,
                color = DriverXyColors.Border.Wrong,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreenContent(
        viewState = SettingsState(),
        navigateBack = {},
        logout = {},
        deleteAccount = {},
    )
}
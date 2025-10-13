package com.utc.driverxy.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.components.DriverXyButton
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    innerPadding: PaddingValues,
    navigatetoSignIn: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pages = getOnboardingPages()
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
            .background(DriverXyColors.BackGround.BackgroundPrimary),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = pages[page].imageResource),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    Text(
                        text = stringResource(pages[page].title),
                        style = DriverXyTypography.Headline.Large.Bold,
                        color = DriverXyColors.Text.TextPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(pages[page].description),
                        style = DriverXyTypography.Body.Large.Medium,
                        color = DriverXyColors.Text.TextTertiary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 148.dp)
                    .height(20.dp)
                    .background(
                        DriverXyColors.BackGround.BackgroundOnboardingDots,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(start = 12.dp, end = 4.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) {
                        DriverXyColors.Primary.Primary1
                    } else DriverXyColors.Border.BorderDropDown
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(color)
                            .padding(vertical = 6.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        DriverXyButton(
            onClick = {
                if (pagerState.currentPage < pages.size - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    navigatetoSignIn()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .heightIn(56.dp),
            style = DriverXyTypography.Title.Large.SemiBold.copy(
                color = Color.White
            ),
            text = stringResource(R.string.button_continue)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

data class OnboardingPage(
    val title: Int,
    val description: Int,
    val imageResource: Int
)

fun getOnboardingPages(): List<OnboardingPage> {
    return listOf(
        OnboardingPage(
            title = R.string.onboarding_title_1,
            description = R.string.onboarding_descripton_1,
            imageResource = R.drawable.img_onboarding_1
        ),
        OnboardingPage(
            title = R.string.onboarding_title_2,
            description = R.string.onboarding_descripton_2,
            imageResource = R.drawable.img_onboarding_2
        ),
        OnboardingPage(
            title = R.string.onboarding_title_3,
            description = R.string.onboarding_descripton_3,
            imageResource = R.drawable.img_onboarding_3
        ),
        OnboardingPage(
            title = R.string.onboarding_title_4,
            description = R.string.onboarding_descripton_4,
            imageResource = R.drawable.img_onboarding_4
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(
        innerPadding = PaddingValues(),
        navigatetoSignIn = {}
    )
}
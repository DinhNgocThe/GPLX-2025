package com.utc.driverxy.presentation.theme

import androidx.compose.ui.graphics.Color

object DriverXyColors {
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Red = Color(0xFFFF3429)
    val Blue = Color(0xFF007AFF)
    val Green = Color(0xFF34C759)

    object Primary {
        val Primary1 = Color(0xFF0077FF)
        val Primary1Opacity = Color(0xFFEBF5FF)
    }

    object BackGround {
        val BackgroundPrimary = White
        val BackgroundSecondary = Color(0xFFFAFAFA)
        val BackgroundOnboardingDots = Color(0xFFF5F5F5)
        val BackgroundTertiary = Color(0x70F2F2F7)
        val BackgroundTrashBottom = Color(0xFFD9EBFF)
        val BackgroundLightBlue = Color(0xFFF2F8FF)
    }

    object Surface {
        val SurfacePrimary = Color(0xFFF6F6FA)

        val SurfaceNavigationButton = Color(0xB2F2F2F7)
    }

    object Stroke {
        val Stroke1 = Color(0xFFEAEAEA)
    }

    object Border {
        val BorderLight = Color(0xFFE4E4E6)
        val BorderMedium = Color(0xFFD1D1D6)
        val BorderSummaryInactive = Color(0xFFE4E4E6)
        val BorderDropDown = Color(0x8080808C)
    }

    object Text {
        val TextPrimary = Black
        val TextSecondary = Color(0xFF2B2B2B)
        val TextTertiary = Color(0xFF808080)
        val TextDisabled = Color(0xFF999999)
        val Button = White
    }

    object Gray {
        val Gray = Color(0xFFE4E4E6)
        val Gray2 = Color(0xFFAEAEB2)
        val Gray3 = Color(0xFFF7F7F7)
        val Gray4 = Color(0xFFEEEEEF)
        val Gray5 = Color(0xFF8E8E93)

        val OnGray = Color(0xFFB2B2B5)
    }

    object Gradient {
        val PrimaryGradient = listOf(
            Color(0xFF0077FF),
            Color(0xFF41A1FF),
            Color(0xFF4C8AFF),
        )

        val linearGradient = listOf(
            Color(0xFF509DF5),
            Color(0xFF59ADFF),
            Color(0xFF2F76FF)
        )

        val Gradient2 = listOf(
            Color(0xFFFF8026),
            Color(0xFFFF37C3)
        )
        val BannerGradient = listOf(
            Color(0xFF8CC0FB),
            Color(0xFF73B6F8),
            Color(0xFF5D93FA),
        )
    }

    object Labels {
        val LabelsPrimary = Black
        val LabelsSecondary = Color(0x993C3C43)
    }

    object TabBar {
        val TabBarBackground = White
        val TabBarBorder = Color(0xFFEAEAEA)
        val TabBarSelectedBackground = Color(0xFFEDEDED)
        val TabBarSelectedText = Color(0xFF0088FF)
        val TabBarUnselectedText = Color(0xFF404040)
        val TabBarFabBackground = Color(0xFF2D92FF)
        val TabBarFabIcon = White
    }

    object Schemes {
        val OnSurface = Color(0xFF1D1B20)
        val OnSurfaceVariant = Color(0xFF49454F)

        val Outline = Color(0xFF79747E)
    }

    object Fills {
        val Tertiary = Color(0x1F787880)
        val FillsSecondary = Color(0xFF787880)
    }

    object Error {
        val ErrorDark = Color(0xFFFFB4AB)
        val OnErrorDark = Color(0xFF690005)
        val ErrorContainerDark = Color(0xFF93000A)
        val OnErrorContainerDark = Color(0xFFFFDAD6)
    }

    object Neutral {
        val Neutral00 = Color(0xFF1A1A1A)
    }
}

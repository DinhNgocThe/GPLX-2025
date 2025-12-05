package com.utc.driverxy.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp
import com.utc.driverxy.R

val AppFontRegular = FontFamily(Font(R.font.plus_jakarta_sans_regular, weight = FontWeight.Normal))
val AppFontMedium = FontFamily(Font(R.font.plus_jakarta_sans_medium, weight = FontWeight.Medium))
val AppFontSemiBold = FontFamily(Font(R.font.plus_jakarta_sans_semi, weight = FontWeight.SemiBold))
val AppFontBold = FontFamily(Font(R.font.plus_jakarta_sans_bold, weight = FontWeight.Bold))

object DriverXyTypography {
    object Headline {
        object Large {
            val Medium = TextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = AppFontMedium,
                lineBreak = LineBreak.Simple
            )
            val SemiBold = TextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
            val Bold = TextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFontBold,
                lineBreak = LineBreak.Simple
            )
        }

        object Medium {
            val SemiBold = TextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
            val Bold = TextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFontBold,
                lineBreak = LineBreak.Simple
            )
        }

        object Small {
            val SemiBold = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
            val Bold = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFontBold,
                lineBreak = LineBreak.Simple
            )
        }
    }

    object Title {
        object Large {
            val Medium = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = AppFontMedium,
                lineBreak = LineBreak.Simple
            )
            val SemiBold = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
            val Bold = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFontBold,
                lineBreak = LineBreak.Simple
            )
        }

        object Medium {
            val Medium = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = AppFontMedium,
                lineBreak = LineBreak.Simple
            )
            val SemiBold = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
            val Bold = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFontBold,
                lineBreak = LineBreak.Simple
            )
        }

        object Small {
            val Medium = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = AppFontMedium,
                lineBreak = LineBreak.Simple
            )
            val SemiBold = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
            val Bold = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFontBold,
                lineBreak = LineBreak.Simple
            )
        }
    }

    object Body {
        object Large {
            val Regular = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = AppFontRegular,
                lineBreak = LineBreak.Simple
            )

            val Medium = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = AppFontMedium,
                lineBreak = LineBreak.Simple
            )
        }

        val Medium = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = AppFontMedium,
            lineBreak = LineBreak.Simple
        )

        object Small {
            val Medium = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = AppFontMedium,
                lineBreak = LineBreak.Simple
            )

            val SemiBold = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFontSemiBold,
                lineBreak = LineBreak.Simple
            )
        }
    }
}
package com.jesil.spark.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jesil.spark.R

// Set of Material typography styles to start with
val AkayaKanadaka = FontFamily(
    Font(R.font.akaya_kanadaka, FontWeight.Normal)
)

val Typography = Typography(
    // The main Quote text on the screen
    displayLarge = TextStyle(
        fontFamily = AkayaKanadaka,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),

    // Used for the Main Quote Text
    displayMedium = TextStyle(
        fontFamily = AkayaKanadaka,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    // Used for shorter quotes or author names
    headlineSmall = TextStyle(
        fontFamily = AkayaKanadaka,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // Standard reading text
    bodyLarge = TextStyle(
        fontFamily = AkayaKanadaka,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    ),

    // UI elements (Keep as Default for better legibility)
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
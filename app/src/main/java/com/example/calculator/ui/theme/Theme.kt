package com.example.calculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorScheme(
        primary = DarkButton,
        onPrimary = DarkButtonText,
        background = DarkBackground,
        surface = DarkResultBackground,
        onSurface = DarkText
    ) else lightColorScheme(
        primary = LightButton,
        onPrimary = LightButtonText,
        background = LightBackground,
        surface = LightResultBackground,
        onSurface = LightText
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}

val ButtonColor: Color
    @Composable get() = MaterialTheme.colorScheme.primary

val OperatorButtonColor: Color
    @Composable get() = MaterialTheme.colorScheme.secondaryContainer

val ButtonTextColor: Color
    @Composable get() = MaterialTheme.colorScheme.onPrimary
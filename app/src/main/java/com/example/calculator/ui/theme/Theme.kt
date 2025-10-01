package com.example.calculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorScheme(
        primary = DarkButton,
        onPrimary = DarkButtonText,
        secondaryContainer = DarkOperatorButton,
        background = DarkBackground,
        surface = DarkResultBackground,
        onSurface = DarkText
    ) else lightColorScheme(
        primary = LightButton,
        onPrimary = LightButtonText,
        secondaryContainer = LightOperatorButton,
        background = LightBackground,
        surface = LightResultBackground,
        onSurface = LightText
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

val ButtonColor: Color
    @Composable get() = MaterialTheme.colorScheme.primary

val OperatorButtonColor: Color
    @Composable get() = MaterialTheme.colorScheme.secondaryContainer

val EqualsButtonColor: Color
    @Composable get() = if (isSystemInDarkTheme()) DarkEqualsButton else LightEqualsButton

val ButtonTextColor: Color
    @Composable get() = MaterialTheme.colorScheme.onPrimary

val ResultTextColor: Color
    @Composable get() = if (isSystemInDarkTheme()) DarkResultText else LightResultText
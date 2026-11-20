package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
  darkColorScheme(
    primary = SaffronLight,
    onPrimary = Color.Black,
    primaryContainer = SaffronDark,
    onPrimaryContainer = Color.White,
    secondary = TempleGold,
    onSecondary = Color.Black,
    secondaryContainer = DevotionalDarkCard,
    onSecondaryContainer = TempleGoldLight,
    tertiary = MarigoldAmber,
    background = DevotionalDarkBg,
    onBackground = ChandanCream,
    surface = DevotionalDarkSurface,
    onSurface = ChandanCream,
    surfaceVariant = DevotionalDarkCard,
    onSurfaceVariant = TempleGoldLight,
    outline = DevotionalDarkBorder
  )

private val LightColorScheme =
  lightColorScheme(
    primary = SaffronPrimary,
    onPrimary = Color.White,
    primaryContainer = ChandanCardBorder,
    onPrimaryContainer = SaffronDark,
    secondary = SaffronDark,
    onSecondary = Color.White,
    secondaryContainer = ChandanCard,
    onSecondaryContainer = SaffronDark,
    tertiary = SindoorCrimson,
    background = ChandanCream,
    onBackground = Color(0xFF20130E),
    surface = Color.White,
    onSurface = Color(0xFF20130E),
    surfaceVariant = ChandanCard,
    onSurfaceVariant = Color(0xFF4A322A),
    outline = ChandanCardBorder
  )

@Composable
fun TheGajananamTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

// Backward compatibility alias for tests
@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  TheGajananamTheme(darkTheme = darkTheme, content = content)
}


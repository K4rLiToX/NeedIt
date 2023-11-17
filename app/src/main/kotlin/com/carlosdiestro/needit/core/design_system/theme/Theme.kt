package com.carlosdiestro.needit.core.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ni_theme_dark_primary,
    onPrimary = ni_theme_dark_onPrimary,
    primaryContainer = ni_theme_dark_primaryContainer,
    onPrimaryContainer = ni_theme_dark_onPrimaryContainer,
    secondary = ni_theme_dark_secondary,
    onSecondary = ni_theme_dark_onSecondary,
    secondaryContainer = ni_theme_dark_secondaryContainer,
    onSecondaryContainer = ni_theme_dark_onSecondaryContainer,
    tertiary = ni_theme_dark_tertiary,
    onTertiary = ni_theme_dark_onTertiary,
    tertiaryContainer = ni_theme_dark_tertiaryContainer,
    onTertiaryContainer = ni_theme_dark_onTertiaryContainer,
    error = ni_theme_dark_error,
    errorContainer = ni_theme_dark_errorContainer,
    onError = ni_theme_dark_onError,
    onErrorContainer = ni_theme_dark_onErrorContainer,
    background = ni_theme_dark_background,
    onBackground = ni_theme_dark_onBackground,
    surface = ni_theme_dark_surface,
    onSurface = ni_theme_dark_onSurface,
    surfaceVariant = ni_theme_dark_surfaceVariant,
    onSurfaceVariant = ni_theme_dark_onSurfaceVariant,
    outline = ni_theme_dark_outline,
    inverseOnSurface = ni_theme_dark_inverseOnSurface,
    inverseSurface = ni_theme_dark_inverseSurface,
    inversePrimary = ni_theme_dark_inversePrimary,
    surfaceTint = ni_theme_dark_surfaceTint,
    outlineVariant = ni_theme_dark_outlineVariant,
    scrim = ni_theme_dark_scrim,
)

private val LightColorScheme = lightColorScheme(
    primary = ni_theme_light_primary,
    onPrimary = ni_theme_light_onPrimary,
    primaryContainer = ni_theme_light_primaryContainer,
    onPrimaryContainer = ni_theme_light_onPrimaryContainer,
    secondary = ni_theme_light_secondary,
    onSecondary = ni_theme_light_onSecondary,
    secondaryContainer = ni_theme_light_secondaryContainer,
    onSecondaryContainer = ni_theme_light_onSecondaryContainer,
    tertiary = ni_theme_light_tertiary,
    onTertiary = ni_theme_light_onTertiary,
    tertiaryContainer = ni_theme_light_tertiaryContainer,
    onTertiaryContainer = ni_theme_light_onTertiaryContainer,
    error = ni_theme_light_error,
    errorContainer = ni_theme_light_errorContainer,
    onError = ni_theme_light_onError,
    onErrorContainer = ni_theme_light_onErrorContainer,
    background = ni_theme_light_background,
    onBackground = ni_theme_light_onBackground,
    surface = ni_theme_light_surface,
    onSurface = ni_theme_light_onSurface,
    surfaceVariant = ni_theme_light_surfaceVariant,
    onSurfaceVariant = ni_theme_light_onSurfaceVariant,
    outline = ni_theme_light_outline,
    inverseOnSurface = ni_theme_light_inverseOnSurface,
    inverseSurface = ni_theme_light_inverseSurface,
    inversePrimary = ni_theme_light_inversePrimary,
    surfaceTint = ni_theme_light_surfaceTint,
    outlineVariant = ni_theme_light_outlineVariant,
    scrim = ni_theme_light_scrim,
)

@Composable
fun NeedItTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
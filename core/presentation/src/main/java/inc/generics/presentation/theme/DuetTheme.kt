package inc.generics.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import inc.generics.presentation.theme.colors.DuetColors
import inc.generics.presentation.theme.localization.Language

object DuetTheme {
    val colors: DuetColors
        @Composable
        get() = LocaleDuetColors.current

    val localization: Language
        @Composable
        get() = LocaleDuetLocalization.current
}

internal val LocaleDuetColors = staticCompositionLocalOf<DuetColors> {
    error("No duet Colors provided")
}

internal val LocaleDuetLocalization = staticCompositionLocalOf<Language> {
    error("No duet Language provided")
}
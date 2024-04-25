package inc.generics.presentation.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import inc.generics.presentation.theme.localization.Language
import inc.generics.presentation.theme.colors.defaultDarkPalette
import inc.generics.presentation.theme.colors.defaultLightPalette

@Composable
fun AppDuetTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    localization: Language = Language.En(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) defaultDarkPalette else defaultLightPalette
    val rippleIndication = rememberRipple()

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.mainColor,
            darkIcons = !useDarkTheme
        )
    }

    CompositionLocalProvider(
        LocaleDuetColors provides colors,
        LocalIndication provides rippleIndication,
        LocaleDuetLocalization provides localization,
        content = content
    )
}
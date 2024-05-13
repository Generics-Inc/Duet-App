package inc.generics.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import inc.generics.presentation.theme.DuetTheme

@Composable
fun defaultTextStyleDuet(): TextStyle = TextStyle(
    color = DuetTheme.colors.textColor,
    fontSize = 18.sp,
    textAlign = TextAlign.Center
)

@Composable
fun titleContrastTestStyleDuet(): TextStyle = TextStyle(
    color = DuetTheme.colors.backgroundColor,
    fontSize = 20.sp,
    textAlign = TextAlign.End
)

@Composable
fun defaultDialogTextStyleDuet(): TextStyle = TextStyle(
    color = DuetTheme.colors.textColor,
    fontSize = 14.sp,
)

@Composable
internal fun defaultTextStyleForButtonDuet(): TextStyle = TextStyle(
    fontSize = 18.sp,
    textAlign = TextAlign.Center
)
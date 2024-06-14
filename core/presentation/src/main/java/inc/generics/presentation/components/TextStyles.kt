package inc.generics.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
fun secondTextStyleDuet(): TextStyle = TextStyle(
    color = DuetTheme.colors.textColor.copy(alpha = 0.5f),
    fontSize = 12.sp,
    textAlign = TextAlign.Center
)

@Composable
fun titleContrastTestStyleDuet(): TextStyle = TextStyle(
    color = DuetTheme.colors.backgroundColor,
    fontSize = 20.sp,
    textAlign = TextAlign.End,
    fontWeight = FontWeight.Bold
)

@Composable
fun defaultDialogTextStyleDuet(): TextStyle = TextStyle(
    color = DuetTheme.colors.textColor,
    fontSize = 14.sp,
)

@Composable
fun defaultTextStyleForButtonDuet(): TextStyle = TextStyle(
    fontSize = 18.sp,
    textAlign = TextAlign.Center
)
package inc.generics.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.generics.presentation.theme.DuetTheme

@Composable
fun OutlinedButtonDuet(
    modifier: Modifier = Modifier,
    hasBorder: Boolean = true,
    hasElevation: Boolean = true,
    onClick: () -> Unit, content: @Composable() (RowScope.() -> Unit)
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = DuetTheme.colors.backgroundColor,
            contentColor = DuetTheme.colors.secondColor
        ),
        border = if (hasBorder) BorderStroke(1.dp, DuetTheme.colors.secondColor) else null,
        elevation = if(hasElevation)
            ButtonDefaults.elevatedButtonElevation(2.dp, 0.dp, 0.dp, 0.dp, 2.dp) else null,
        content = content
    )
}

@Composable
fun FilledTonalButtonDuet(
    modifier: Modifier = Modifier,
    color: Color = DuetTheme.colors.secondColor,
    onClick: () -> Unit, content: @Composable() (RowScope.() -> Unit)
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color,
            contentColor = DuetTheme.colors.textContrastColor
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp, 0.dp, 0.dp, 0.dp, 2.dp),
        content = content
    )
}
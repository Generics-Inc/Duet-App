package inc.generics.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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

@Composable
fun DefaultOutlinedButtonDuet(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier
) {
    OutlinedButtonDuet(
        onClick = onClick,
        modifier = modifier.height(45.dp)
    ) {
        Text(
            text = text,
            style = defaultTextStyleForButtonDuet(),
        )
    }
}

@Composable
fun DefaultFilledTonalButtonDuet(
    onClick: () -> Unit,
    text: String,
    color: Color = DuetTheme.colors.secondColor,
    modifier: Modifier
) {
    FilledTonalButtonDuet(
        onClick = onClick,
        modifier = modifier.size(187.dp, 45.dp),
        color = color
    ) {
        Text(
            text = text,
            style = defaultTextStyleForButtonDuet()
        )
    }
}
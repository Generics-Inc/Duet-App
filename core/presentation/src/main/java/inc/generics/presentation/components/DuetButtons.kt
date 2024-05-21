package inc.generics.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.theme.DuetTheme

@Composable
fun OutlinedButtonDuet(
    modifier: Modifier = Modifier,
    hasBorder: Boolean = true,
    hasElevation: Boolean = true,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.outlinedShape,
    onClick: () -> Unit, content: @Composable() (RowScope.() -> Unit)
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = DuetTheme.colors.backgroundColor,
            contentColor = DuetTheme.colors.secondColor
        ),
        border = if (hasBorder) BorderStroke(1.dp, DuetTheme.colors.secondColor) else null,
        elevation = if(hasElevation)
            ButtonDefaults.elevatedButtonElevation(2.dp, 0.dp, 0.dp, 0.dp, 2.dp) else null,
        shape = shape,
        content = content
    )
}

@Composable
fun FilledTonalButtonDuet(
    modifier: Modifier = Modifier,
    color: Color = DuetTheme.colors.secondColor,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.outlinedShape,
    onClick: () -> Unit, content: @Composable() (RowScope.() -> Unit)
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
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
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButtonDuet(
        onClick = onClick,
        modifier = modifier.size(187.dp, 45.dp),
        enabled = enabled
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
    modifier: Modifier = Modifier,
    color: Color = DuetTheme.colors.secondColor,
    enabled: Boolean = true
) {
    FilledTonalButtonDuet(
        onClick = onClick,
        modifier = modifier.size(187.dp, 45.dp),
        color = color,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = defaultTextStyleForButtonDuet()
        )
    }
}

@Composable
fun DefaultDialogOutlinedButtonDuet(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButtonDuet(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        hasBorder = false,
        hasElevation = false
    ) {
        Text(
            text = text,
            style = defaultTextStyleForButtonDuet().copy(fontSize = 14.sp),
        )
    }
}

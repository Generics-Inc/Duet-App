package inc.generics.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.CutterType
import inc.generics.presentation.utils.EventsCutter
import inc.generics.presentation.utils.OnlyOneClickCutter
import inc.generics.presentation.utils.get

@Composable
fun OutlinedButtonDuet(
    modifier: Modifier = Modifier,
    hasBorder: Boolean = true,
    hasElevation: Boolean = true,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(10.dp),
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS,
    onClick: () -> Unit, content: @Composable (RowScope.() -> Unit)
) {
    val eventsCutter by remember {
        mutableStateOf(
            EventsCutter.get(cutterType)
        )
    }
    val innerEnabled: Boolean = (eventsCutter as? OnlyOneClickCutter)?.isClickable ?: enabled

    OutlinedButton(
        onClick = { eventsCutter?.processEvent { onClick() } ?: onClick() },
        modifier = modifier,
        enabled = innerEnabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = DuetTheme.colors.backgroundColor,
            contentColor = DuetTheme.colors.secondColor,
            disabledContainerColor = Color(0xFFD5C5D2) //E6D5E3м
        ),
        border = if (hasBorder && innerEnabled) BorderStroke(1.dp, DuetTheme.colors.secondColor) else null,
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
    shape: Shape = RoundedCornerShape(10.dp),
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS,
    onClick: () -> Unit, content: @Composable (RowScope.() -> Unit)
) {
    val eventsCutter by remember {
        mutableStateOf(
            EventsCutter.get(cutterType)
        )
    }
    val innerEnabled: Boolean = (eventsCutter as? OnlyOneClickCutter)?.isClickable ?: enabled

    FilledTonalButton(
        onClick = { eventsCutter?.processEvent { onClick() } ?: onClick() },
        modifier = modifier,
        enabled = innerEnabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color,
            contentColor = DuetTheme.colors.textContrastColor,
            disabledContainerColor = Color(0xFFD5D1D4) //E6D5E3м
        ),
        shape = shape,
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp, 0.dp, 0.dp, 0.dp, 2.dp),
        content = content
    )
}

@Composable
fun DefaultOutlinedButtonDuet(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS
) {
    OutlinedButtonDuet(
        onClick = onClick,
        modifier = modifier.size(187.dp, 45.dp),
        enabled = enabled,
        cutterType = cutterType
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
    enabled: Boolean = true,
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS
) {
    FilledTonalButtonDuet(
        onClick = onClick,
        modifier = modifier.size(187.dp, 45.dp),
        color = color,
        enabled = enabled,
        cutterType = cutterType
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
    enabled: Boolean = true,
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS
) {
    OutlinedButtonDuet(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        hasBorder = false,
        hasElevation = false,
        cutterType = cutterType
    ) {
        Text(
            text = text,
            style = defaultTextStyleForButtonDuet().copy(fontSize = 14.sp),
        )
    }
}

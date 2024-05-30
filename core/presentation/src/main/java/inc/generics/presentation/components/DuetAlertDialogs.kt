package inc.generics.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys.*

@Composable
fun DuetAlertDialogError(
    messageText: String,
    onClose: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painterResource(id = R.drawable.ic_alert),
                contentDescription = "error icon",
                tint = DuetTheme.colors.errorColor
            )
        },
        title = {
            Text(
                text = DuetTheme.localization[DIALOG_ERROR_TITLE],
                style = defaultDialogTextStyleDuet().copy(
                    color = DuetTheme.colors.errorColor,
                    fontSize = 18.sp
                )
            )
        },
        text = {
            Text(
                text = messageText,
                style = defaultDialogTextStyleDuet().copy(color = DuetTheme.colors.errorColor)
            )
        },
        onDismissRequest = { onClose() },
        confirmButton = {
            DefaultDialogOutlinedButtonDuet(
                onClick = { onClose() },
                text = DuetTheme.localization[OK_ON_ERROR]
            )
        },
        containerColor = DuetTheme.colors.backgroundColor
    )
}
@Composable
fun DuetAlertDialogMessage(
    icon:  @Composable (() -> Unit),
    messageText: String,
    onClose: () -> Unit
) {
    AlertDialog(
        icon = {
            icon()
        },
        title = {},
        text = {
            Text(
                text = messageText,
                style = defaultDialogTextStyleDuet().copy()
            )
        },
        onDismissRequest = { onClose() },
        confirmButton = {
            DefaultDialogOutlinedButtonDuet(
                onClick = { onClose() },
                text = DuetTheme.localization[OK]
            )
        },
        containerColor = DuetTheme.colors.backgroundColor
    )
}


@Composable
fun DuetAlertDialogRequest(
    messageText: String,
    onClose: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painterResource(id = R.drawable.ic_question),
                contentDescription = "question icon",
                tint = DuetTheme.colors.mainColor
            )
        },
        title = {
            Text(
                text = DuetTheme.localization[TITLE_REQUEST_DIALOG],
                style = defaultTextStyleDuet()
            )
        },
        text = {
            Text(
                text = messageText,
                style = defaultDialogTextStyleDuet()
            )
        },
        onDismissRequest = { onClose() },
        confirmButton = {
            DefaultDialogOutlinedButtonDuet(
                onClick = { onClose() },
                text = DuetTheme.localization[YES]
            )
        },
        containerColor = DuetTheme.colors.backgroundColor
    )
}
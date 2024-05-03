package inc.generics.presentation.components

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import inc.generics.presentation.theme.DuetTheme

@Composable
fun defaultTextFieldStyle(): TextFieldColors = TextFieldDefaults.colors().copy(
    unfocusedContainerColor = DuetTheme.colors.secondColor.copy(alpha = 0.4f),
    focusedContainerColor = DuetTheme.colors.secondColor.copy(alpha = 0.4f),
    errorContainerColor = DuetTheme.colors.errorColor,
    unfocusedLabelColor = DuetTheme.colors.thirdColor.copy(alpha = 0.5f),
    focusedLabelColor = DuetTheme.colors.thirdColor,
    unfocusedIndicatorColor = DuetTheme.colors.thirdColor.copy(alpha = 0.5f),
    focusedIndicatorColor = DuetTheme.colors.thirdColor,
    cursorColor = DuetTheme.colors.thirdColor,
    errorTextColor = DuetTheme.colors.errorColor,
    unfocusedSupportingTextColor = DuetTheme.colors.secondColor,
    focusedSupportingTextColor = DuetTheme.colors.secondColor,
    focusedTextColor = DuetTheme.colors.thirdColor,
    unfocusedTextColor = DuetTheme.colors.thirdColor
)
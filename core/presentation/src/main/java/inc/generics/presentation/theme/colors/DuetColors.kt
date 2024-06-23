package inc.generics.presentation.theme.colors

import androidx.compose.ui.graphics.Color

data class DuetColors(
    val themeName: String,
    val mainColor: Color,
    val secondColor: Color,
    val thirdColor: Color,
    val backgroundColor: Color,
    val textColor: Color,
    val textContrastColor: Color,
    val errorColor: Color,
    val successColor: Color,
    val hdRezkaColor: HdRezkaColor
)

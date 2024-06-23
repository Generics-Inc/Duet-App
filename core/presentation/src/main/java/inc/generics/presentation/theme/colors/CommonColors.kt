package inc.generics.presentation.theme.colors

import androidx.compose.ui.graphics.Color

data class VkColors(
    val mainColor: Color = Color(0xFF0077FF)
)

data class HdRezkaColor(
    val mainColor: Color,
    val secondColor: Color
)

val vkColors = VkColors()
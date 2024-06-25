package inc.generics.movie_in_detail.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import inc.generics.presentation.theme.DuetTheme

data class DataByTypeMovie(
    val name: String,
    val brush: Brush
)

@Composable
fun create(typeMovie: TypeMoviePart): DataByTypeMovie {
    return when (typeMovie) {
        TypeMoviePart.FILM -> DataByTypeMovie(
            name = "Фильм",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.mainColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )

        TypeMoviePart.SERIAL -> DataByTypeMovie(
            name = "Сериал",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.secondColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )

        TypeMoviePart.ANIME -> DataByTypeMovie(
            name = "Аниме",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.thirdColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )

        TypeMoviePart.CARTOON -> DataByTypeMovie(
            name = "Мультфильм",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.successColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )
    }
}

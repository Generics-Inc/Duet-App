package inc.generics.new_movie_hdrezka.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import inc.generics.presentation.theme.DuetTheme

data class DataByTypeMovie(
    val name: String,
    val brush: Brush
)


@Composable
fun create(typeMovie: TypeMovie): DataByTypeMovie {
    return when(typeMovie) {
        TypeMovie.FILM -> DataByTypeMovie(
            name = "Фильм",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.mainColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )
        TypeMovie.SERIAL -> DataByTypeMovie(
            name = "Сериал",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.secondColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )
        TypeMovie.ANIME -> DataByTypeMovie(
            name = "Аниме",
            brush = Brush.linearGradient(
                listOf(
                    DuetTheme.colors.thirdColor,
                    DuetTheme.colors.backgroundColor
                )
            )
        )
        TypeMovie.CARTOON -> DataByTypeMovie(
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
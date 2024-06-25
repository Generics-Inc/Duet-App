package inc.generics.movie_in_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.movie_in_detail.models.TypeMoviePart
import inc.generics.movie_in_detail.models.create
import inc.generics.presentation.theme.DuetTheme

@Composable
internal fun BoxTypeMovieByType(typeMovie: TypeMoviePart) {
    val dataTypeMovie = create(typeMovie)

    Box(
        modifier = Modifier
            .padding(start = 12.dp)
            .size(60.dp).background(
                shape = RoundedCornerShape(8.dp),
                brush = dataTypeMovie.brush
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = dataTypeMovie.name,
            textAlign = TextAlign.Center,
            fontSize = 9.sp,
            color = DuetTheme.colors.backgroundColor
        )
    }
}
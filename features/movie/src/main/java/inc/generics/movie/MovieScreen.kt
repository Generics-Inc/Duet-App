package inc.generics.movie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.generics.movie.models.Movie
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.theme.DuetTheme

@Composable
fun MovieScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet(
                clickOnProfile = {}
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(DuetTheme.colors.backgroundColor).padding(it)
        ) {
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 1",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 2",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 3",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 1",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 2",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 3",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 1",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 2",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
            item {
                MovieItem(Movie(
                    "ФИЛЬМ 3",
                    "описание описание описание описание описание описаниеописание описаниеописание описаниеописание описание",
                    false
                ))
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        Surface(
            modifier = Modifier.size(width = 100.dp, height = 160.dp),
            color = DuetTheme.colors.backgroundColor,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, DuetTheme.colors.mainColor)
        ) {

        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = movie.name, modifier.fillMaxWidth())
            Text(text = movie.description, modifier.fillMaxWidth())
        }
    }
}
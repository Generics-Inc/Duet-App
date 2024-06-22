package inc.generics.movie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.movie.models.Movie
import inc.generics.movie.routing.MovieRouting
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.DuetAsyncImageOpen
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.theme.DuetTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(
    routing: MovieRouting
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet(
                clickOnProfile = {
                    routing.onProfile()
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(DuetTheme.colors.backgroundColor).padding(it)
        ) {
            stickyHeader {
                HeaderList()
            }
        }
    }
}


@Composable
internal fun HeaderList() {
    Row(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .background(DuetTheme.colors.thirdColor, shape = RoundedCornerShape(8.dp))
        .padding(horizontal = 4.dp)
    ) {
        OutlinedButtonDuet(
            modifier = Modifier.weight(1f),
            hasBorder = false,
            hasElevation = false,
            containerColor = DuetTheme.colors.thirdColor,
            contentColor = DuetTheme.colors.backgroundColor,
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(inc.generics.presentation.R.drawable.ic_find),
                contentDescription = "",
                tint = DuetTheme.colors.backgroundColor
            )
        }

        OutlinedButtonDuet(
            modifier = Modifier.weight(1f),
            hasBorder = false,
            hasElevation = false,
            containerColor = DuetTheme.colors.thirdColor,
            contentColor = DuetTheme.colors.backgroundColor,
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(inc.generics.presentation.R.drawable.ic_filter),
                contentDescription = "",
                tint = DuetTheme.colors.backgroundColor
            )
        }

        OutlinedButtonDuet(
            modifier = Modifier.weight(1f),
            hasBorder = false,
            hasElevation = false,
            containerColor = DuetTheme.colors.thirdColor,
            contentColor = DuetTheme.colors.backgroundColor,
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(inc.generics.presentation.R.drawable.ic_add),
                contentDescription = "",
                tint = DuetTheme.colors.backgroundColor
            )
        }
    }
}

@Composable
internal fun CommonMovieItem(
    movie: Movie
) {
    val widthPoster = 100.dp
    val heightPoster = 160.dp
    val heightPosterTask = 100.dp

    if (movie.createdMovie != null) {
        MovieItem(
            movie = movie,
            widthPoster = widthPoster,
            heightPoster = heightPoster
        )
    } else if (movie.taskCreate != null) {
        TaskItem(
            movie = movie,
            widthIcon = widthPoster,
            heightIcon = heightPosterTask
        )
    }
}

@Composable
internal fun TaskItem(
    movie: Movie,
    widthIcon: Dp,
    heightIcon: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {

            Surface(
                modifier = Modifier.size(width = widthIcon, height = heightIcon).shadow(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 8.dp,
                ),
                color = DuetTheme.colors.backgroundColor,
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    modifier = Modifier.size(width = widthIcon, height = heightIcon),
                    painter = painterResource(inc.generics.presentation.R.drawable.ic_generating),
                    contentDescription = "genetating",
                    tint = DuetTheme.colors.thirdColor
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .height(heightIcon)
                    .padding(start = 8.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = if (movie.taskCreate!!.isError) "Ошибка создания" else "Создается...",
                    modifier = Modifier.fillMaxWidth(),
                    style = defaultTextStyleDuet().copy(
                        color = if (movie.taskCreate.isError) DuetTheme.colors.errorColor else DuetTheme.colors.successColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    ),
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = movie.taskCreate.name,
                    modifier = Modifier.weight(1f).fillMaxWidth().padding(top = 2.dp),
                    style = defaultTextStyleDuet().copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DuetAsyncImage(
                        painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_profile),
                        colorIcon = DuetTheme.colors.textColor,
                        imgUrl = movie.creator.photoUrl,
                        roundedDp = 4.dp,
                        size = 22.dp,
                        paddingDp = 4.dp
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = movie.creator.name,
                        style = defaultTextStyleDuet().copy(fontSize = 10.sp, textAlign = TextAlign.Start)
                    )

                    Icon(
                        painter = painterResource(inc.generics.presentation.R.drawable.ic_eye),
                        tint = DuetTheme.colors.successColor,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

        }

        if (movie.taskCreate!!.isError) {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                FilledTonalButtonDuet(
                    modifier = Modifier.weight(1f),
                    color = DuetTheme.colors.errorColor,
                    onClick = {}
                ) {
                    Text(text = "Удалить", style = defaultTextStyleForButtonDuet()
                        .copy(fontSize = 14.sp))
                }

                OutlinedButtonDuet(
                    modifier = Modifier.weight(1f).padding(start = 5.dp),
                    onClick = {}
                ) {
                    Text(text = "Повторить", style = defaultTextStyleForButtonDuet().copy(fontSize = 14.sp))
                }
            }

        }
    }
}

@Composable
internal fun MovieItem(
    movie: Movie,
    widthPoster: Dp,
    heightPoster: Dp
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        DuetAsyncImageOpen(
            modifier = Modifier.size(width = widthPoster, height = heightPoster),
            painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_movie_item),
            colorIcon = DuetTheme.colors.thirdColor,
            imgUrl = movie.createdMovie!!.photoUrl
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(heightPoster)
                .padding(start = 8.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = movie.createdMovie.name,
                modifier = Modifier.fillMaxWidth(),
                style = defaultTextStyleDuet().copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = movie.createdMovie.description,
                modifier = Modifier.weight(1f).fillMaxWidth(),
                style = defaultTextStyleDuet().copy(
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DuetAsyncImage(
                    painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_profile),
                    colorIcon = DuetTheme.colors.textColor,
                    imgUrl = movie.creator.photoUrl,
                    roundedDp = 4.dp,
                    size = 22.dp,
                    paddingDp = 4.dp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = movie.creator.name,
                    style = defaultTextStyleDuet().copy(fontSize = 10.sp, textAlign = TextAlign.Start)
                )

                Icon(
                    painter = painterResource(inc.generics.presentation.R.drawable.ic_eye),
                    tint = DuetTheme.colors.successColor,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
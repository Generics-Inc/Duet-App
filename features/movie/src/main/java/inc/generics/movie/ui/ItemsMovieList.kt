package inc.generics.movie.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.movie.vm.ActionItemBottomSheetViewModel
import inc.generics.movie.vm.DataItem
import inc.generics.movie.vm.MovieViewModel
import inc.generics.movie_data.models.Movie
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.DuetAsyncImageOpen
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

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
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel(),
    viewModel: MovieViewModel = koinViewModel(),
    movie: Movie,
    widthIcon: Dp,
    heightIcon: Dp
) {
    Column(
        modifier = Modifier
            .let {
                if (movie.createdMovie != null)
                    it.clip(RoundedCornerShape(8.dp)).clickable {
                        viewModelBottomSheetViewModel.show(
                            DataItem(
                                movie.id,
                                movie.taskCreate!!.name
                            )
                        )
                    }
                else
                    it
            }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {

            Surface(
                modifier = Modifier.size(width = widthIcon, height = heightIcon).shadow(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                ),
                color = DuetTheme.colors.backgroundColor,
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    modifier = Modifier.size(width = widthIcon, height = heightIcon),
                    painter = painterResource(R.drawable.ic_generating),
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
                        color = if (movie.taskCreate!!.isError) DuetTheme.colors.errorColor else DuetTheme.colors.successColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    ),
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = movie.taskCreate!!.name,
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
                        painterIconIfNotImg = painterResource(R.drawable.ic_profile),
                        colorIcon = DuetTheme.colors.textColor,
                        imgUrl = movie.creator.photoUrl,
                        roundedDp = 4.dp,
                        size = 22.dp,
                        paddingDp = 4.dp,
                        shadowElevation = 2.dp
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = movie.creator.name,
                        style = defaultTextStyleDuet().copy(
                            fontSize = 10.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                }
            }

        }

        if (movie.taskCreate!!.isError) {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                FilledTonalButtonDuet(
                    modifier = Modifier.weight(1f),
                    color = DuetTheme.colors.errorColor,
                    onClick = {
                        viewModel.deleteById(id = movie.id)
                    }
                ) {
                    Text(
                        text = "Удалить", style = defaultTextStyleForButtonDuet()
                            .copy(fontSize = 14.sp)
                    )
                }

                OutlinedButtonDuet(
                    modifier = Modifier.weight(1f).padding(start = 5.dp),
                    onClick = {
                        viewModel.tryAgan(taskId = movie.taskCreate!!.id)
                    }
                ) {
                    Text(
                        text = "Повторить",
                        style = defaultTextStyleForButtonDuet().copy(fontSize = 14.sp)
                    )
                }
            }

        }
    }
}

@Composable
internal fun MovieItem(
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel(),
    movie: Movie,
    widthPoster: Dp,
    heightPoster: Dp
) {
    Row(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .clickable {
            viewModelBottomSheetViewModel.show(
                DataItem(
                    id = movie.id,
                    name = movie.createdMovie!!.name
                )
            )
        }
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        DuetAsyncImageOpen(
            modifier = Modifier.size(width = widthPoster, height = heightPoster),
            painterIconIfNotImg = painterResource(R.drawable.ic_movie_item),
            colorIcon = DuetTheme.colors.thirdColor,
            imgUrl = movie.createdMovie!!.photoUrl,
            shadowElevation = 2.dp
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(heightPoster)
                .padding(start = 8.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = movie.createdMovie!!.name,
                modifier = Modifier.weight(1f).fillMaxWidth(),
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
                    painterIconIfNotImg = painterResource(R.drawable.ic_profile),
                    colorIcon = DuetTheme.colors.textColor,
                    imgUrl = movie.creator.photoUrl,
                    roundedDp = 4.dp,
                    size = 22.dp,
                    paddingDp = 4.dp,
                    shadowElevation = 2.dp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = movie.creator.name,
                    style = defaultTextStyleDuet().copy(
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Icon(
                    painter = painterResource(R.drawable.ic_eye),
                    tint = if (movie.createdMovie!!.isWatched) DuetTheme.colors.successColor else DuetTheme.colors.mainColor,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
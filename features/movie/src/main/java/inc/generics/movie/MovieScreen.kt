package inc.generics.movie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import inc.generics.movie.routing.MovieRouting
import inc.generics.movie.vm.ActionItemBottomSheetViewModel
import inc.generics.movie.vm.DataItem
import inc.generics.movie.vm.MovieViewModel
import inc.generics.movie.vm.StateActionItemBottomSheetViewModel
import inc.generics.movie.vm.StatusScreen
import inc.generics.movie_data.models.Movie
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.DuetAsyncImageOpen
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(
    routing: MovieRouting,
    viewModel: MovieViewModel = koinViewModel(),
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAllMovies()
    }

    val status by viewModel.statusScreen.observeAsState(StatusScreen.NONE)
    val listOfMovie by viewModel.listOfMovie.observeAsState(emptyList())
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = status == StatusScreen.LOAD
    )

    val statusBottomSheet by viewModelBottomSheetViewModel
        .state.observeAsState(StateActionItemBottomSheetViewModel.DISMISS)

    if (statusBottomSheet == StateActionItemBottomSheetViewModel.SHOW) {
        SetupActionItemBottomSheet()
    }


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
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.getAllMovies() },
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = DuetTheme.colors.backgroundColor,
                    contentColor = DuetTheme.colors.mainColor
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(DuetTheme.colors.backgroundColor)
                    .padding(it)
            ) {
                stickyHeader {
                    HeaderList()
                }
                items(
                    items = listOfMovie,
                    key = { it.id }
                ) { movie ->
                    CommonMovieItem(
                        movie = movie
                    )
                }
            }
        }
    }
}


@Composable
internal fun HeaderList() {
    Row(
        modifier = Modifier
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
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel(),
    viewModel: MovieViewModel = koinViewModel(),
    movie: Movie,
    widthIcon: Dp,
    heightIcon: Dp
) {
    Column(
        modifier = Modifier
            .let {
                if (!movie.taskCreate!!.isError)
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
                        painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_profile),
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
            painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_movie_item),
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
                    painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_profile),
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
                    painter = painterResource(inc.generics.presentation.R.drawable.ic_eye),
                    tint = if (movie.createdMovie!!.isWatched) DuetTheme.colors.successColor else DuetTheme.colors.mainColor,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupActionItemBottomSheet(
    viewModel: MovieViewModel = koinViewModel(),
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel()
) {
    val movieData by viewModelBottomSheetViewModel.dataItem.observeAsState()

    if (movieData == null) {
        viewModelBottomSheetViewModel.dismiss()
        return
    }

    ModalBottomSheet(
        onDismissRequest = {
            viewModelBottomSheetViewModel.dismiss()
        },
        containerColor = DuetTheme.colors.backgroundColor,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(
                        width = 90.dp,
                        height = 6.dp
                    )
                    .background(DuetTheme.colors.mainColor, RoundedCornerShape(2.dp))
            )
        },
        scrimColor = Color.Gray.copy(alpha = 0.7f)
    ) {
        Column(
            Modifier.fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 22.dp
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButtonDuet(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    },
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = "Подробнее",
                        style = defaultTextStyleForButtonDuet()
                    )
                }

                FilledTonalButtonDuet(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.deleteById(id = movieData!!.id)
                        viewModelBottomSheetViewModel.dismiss()
                    },
                    color = DuetTheme.colors.errorColor
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = "Удалить",
                        style = defaultTextStyleForButtonDuet()
                    )
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                text = "Выбери действие для \"${movieData?.name ?: ""}\"",
                style = secondTextStyleDuet()
            )
        }
    }
}
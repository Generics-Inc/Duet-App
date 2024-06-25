package inc.generics.movie_in_detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.movie_in_detail.models.ItemListParts
import inc.generics.movie_in_detail.routing.MovieInDetailRouting
import inc.generics.movie_in_detail.ui.BoxTypeMovieByType
import inc.generics.movie_in_detail.vm.MovieInDetailViewModel
import inc.generics.movie_in_detail_data.models.CreateMovieHdRezka
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImageOpen
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.MultipleEventsCutter
import inc.generics.presentation.utils.get
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieInDetailScreen(
    viewModel: MovieInDetailViewModel = koinViewModel(),
    idMovie: Long,
    routing: MovieInDetailRouting
) {
    LaunchedEffect(Unit) {
        viewModel.getMovie(idMovie)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleTopAppBarDuet(
                text = "Запись подробно",
                onClickNav = {
                    routing.back()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(DuetTheme.colors.backgroundColor)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PosterHead(
                widthPoster = 120.dp,
                heightPoster = 180.dp
            )
            MovieViewed()
            DeleteMovie(routing = routing)
            Description()
            ListPart(routing = routing)
        }
    }
}

val horizontalPadding = 16.dp

@Composable
fun PosterHead(
    viewModel: MovieInDetailViewModel = koinViewModel(),
    widthPoster: Dp,
    heightPoster: Dp
) {
    val dataScreen by viewModel.dataScreen.observeAsState(null)
    if (dataScreen == null)
        return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .padding(top = 22.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        DuetAsyncImageOpen(
            modifier = Modifier.size(width = widthPoster, height = heightPoster),
            painterIconIfNotImg = painterResource(R.drawable.ic_movie_item),
            colorIcon = DuetTheme.colors.thirdColor,
            imgUrl = dataScreen!!.photoUrl,
            shadowElevation = 2.dp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            Text(
                text = dataScreen!!.name,
                style = defaultTextStyleDuet().copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = dataScreen!!.originalName,
                style = secondTextStyleDuet()
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .horizontalScroll(rememberScrollState()),
                text = dataScreen?.genres?.reduce(operation = { acc, s ->
                    "$acc, $s"
                }) ?: " ",
                style = defaultTextStyleDuet().copy(fontSize = 12.sp),
                maxLines = 1
            )
        }
    }
}

@Composable
fun MovieViewed(viewModel: MovieInDetailViewModel = koinViewModel()) {
    val dataScreen by viewModel.dataScreen.observeAsState(null)
    if (dataScreen == null)
        return

    var isWatchedState by remember {
        mutableStateOf(dataScreen!!.isWatch)
    }

    val colorContent =
        if (isWatchedState)
            DuetTheme.colors.textContrastColor
        else
            DuetTheme.colors.secondColor

    val color =
        if (isWatchedState)
            DuetTheme.colors.successColor
        else
            DuetTheme.colors.backgroundColor

    val text =
        if (isWatchedState)
            "Просмотрено"
        else
            "Не просмотрено"

    val tweenAnimColors = 500
    val tweenAnimText = 500

    FilledTonalButtonDuet(
        modifier = Modifier
            .padding(top = 22.dp)
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .animateContentSize(
                animationSpec = tween(tweenAnimText)
            ),
        hasElevation = isWatchedState,
        contentColor = animateColorAsState(
            targetValue = colorContent,
            animationSpec = tween(tweenAnimColors),
            label = ""
        ).value,
        color = animateColorAsState(
            targetValue = color,
            animationSpec = tween(tweenAnimColors),
            label = ""
        ).value,
        onClick = {
            isWatchedState = !isWatchedState
            viewModel.setWatchedFlag(dataScreen!!.id)
        }
    ) {
        Text(
            text = text,
            style = defaultTextStyleForButtonDuet()
        )
    }
}

@Composable
fun Description() {

}

@Composable
fun DeleteMovie(
    viewModel: MovieInDetailViewModel = koinViewModel(),
    routing: MovieInDetailRouting
) {
    val dataScree by viewModel.dataScreen.observeAsState()

    FilledTonalButtonDuet(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        color = DuetTheme.colors.errorColor,
        onClick = {
            dataScree?.let {
                viewModel.deleteMovie(id = it.id)
                routing.back()
            }
        }
    ) {
        Text(
            text = "Удалить",
            style = defaultTextStyleForButtonDuet()
        )
    }
}

@Composable
internal fun ListPartItem(
    listPartItem: ItemListParts,
    onClickItem: () -> Unit
) {
    val cutter by remember {
        mutableStateOf(MultipleEventsCutter.get())
    }

    Row(
        modifier = Modifier
            .padding(horizontal = horizontalPadding, vertical = 8.dp)
            .shadow(
                shape = RoundedCornerShape(8.dp),
                ambientColor = DefaultShadowColor,
                spotColor = DefaultShadowColor,
                elevation = 2.dp,
            )
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(8.dp),
                color = DuetTheme.colors.backgroundColor
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                cutter.processEvent { onClickItem() }
            }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        listPartItem.getType()?.let {
            BoxTypeMovieByType(it)
        }

        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 10.dp)
        ) {
            Text(
                text = listPartItem.name,
                style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start, fontSize = 16.sp)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = listPartItem.addName,
                style = secondTextStyleDuet().copy(textAlign = TextAlign.Start)
            )
        }

        Box(
            modifier = Modifier
                .size(width = 50.dp, 30.dp)
                .padding(end = 12.dp)
                .background(
                    shape = RoundedCornerShape(4.dp),
                    color = DuetTheme.colors.thirdColor
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "${listPartItem.rating}",
                style = secondTextStyleDuet().copy(color = DuetTheme.colors.backgroundColor)
            )
        }
    }
}

@Composable
internal fun ListPart(
    viewModel: MovieInDetailViewModel = koinViewModel(),
    routing: MovieInDetailRouting
) {
    val dataScreen by viewModel.dataScreen.observeAsState()
    if (dataScreen?.parts.isNullOrEmpty())
        Text(
            text = "Нет других частей",
            style = defaultTextStyleDuet(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding)
                .padding(top = 16.dp)
        )
    else
        Text(
            text = "Другие части", style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding)
                .padding(top = 16.dp)
        )

    dataScreen?.parts?.let { notNullList ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
        ) {
            items(
                items = notNullList,
                key = { it.link }
            ) {
                ListPartItem(
                    listPartItem = ItemListParts(
                        name = it.name,
                        addName = it.releaseYear.toString(),
                        url = it.link,
                        type = it.type,
                        rating = it.rating
                    ),
                    onClickItem = {
                        viewModel.createByLink(
                            CreateMovieHdRezka(
                                link = it.link,
                                name = it.name,
                                addName = it.releaseYear.toString(),
                                type = it.type
                            )
                        )
                        routing.back()
                    }
                )
            }
        }
    }

}

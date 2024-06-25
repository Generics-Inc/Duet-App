package inc.generics.movie_in_detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.movie_in_detail.vm.MovieInDetailViewModel
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImageOpen
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieInDetailScreen(
    viewModel: MovieInDetailViewModel = koinViewModel(),
    idMovie: Long
) {
    LaunchedEffect(Unit) {
        viewModel.getMovie(idMovie)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleTopAppBarDuet(
                text = "Запись подробно",
                onClickNav = {}
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
            Description()
            PartsList()
            DeleteMovie()
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
                style = defaultTextStyleDuet().copy(fontWeight = FontWeight.Bold)
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
                text = dataScreen!!.originalName,
                style = defaultTextStyleDuet().copy(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun MovieViewed(viewModel: MovieInDetailViewModel = koinViewModel()) {
    val dataScreen by viewModel.dataScreen.observeAsState(null)
    if (dataScreen == null)
        return

    val colorContent =
        if (dataScreen!!.isWatch)
            DuetTheme.colors.textContrastColor
        else
            DuetTheme.colors.secondColor

    val color =
        if (dataScreen!!.isWatch)
            DuetTheme.colors.successColor
        else
            DuetTheme.colors.backgroundColor

    val text =
        if (dataScreen!!.isWatch)
            "Просмотрено"
        else
            "Не просмотрено"

    val tweenAnimColors = 500
    val tweenAnimText = 500

    FilledTonalButtonDuet(
        modifier = Modifier
            .padding(top = 22.dp)
            .padding(horizontal = horizontalPadding)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(tweenAnimText)
            ),
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
        onClick = {}
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
fun PartsList() {

}

@Composable
fun DeleteMovie() {

}


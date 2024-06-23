package inc.generics.new_movie_hdrezka

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.new_movie_hdrezka.models.CreateMovieHdRezka
import inc.generics.new_movie_hdrezka.models.ItemSearch
import inc.generics.new_movie_hdrezka.models.TypeMovie
import inc.generics.new_movie_hdrezka.models.create
import inc.generics.new_movie_hdrezka.routing.NewMovieHdRezkaRouting
import inc.generics.new_movie_hdrezka.vm.NewMovieHdRezkaViewModel
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.boldTextStyleDuet
import inc.generics.presentation.components.defaultTextFieldStyle
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.MultipleEventsCutter
import inc.generics.presentation.utils.get
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewMovieHdRezkaScreen(
    routing: NewMovieHdRezkaRouting
) {
    Scaffold(
        topBar = {
            TitleTopAppBarDuet(
                text = "Новая запись",
                onClickNav = {
                    routing.back()
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DuetTheme.colors.backgroundColor)
        ) {
            Title()
            SearchTextField()
            ListWithResultSearch(routing = routing)
        }
    }
}

val horizontalPadding = 16.dp

@Composable
internal fun Title() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = horizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Поиск в базе фильмов",
            style = boldTextStyleDuet()
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = "Введи название, чтобы увидеть\n" +
                    "список доступного контента.",
            style = defaultTextStyleDuet().copy(fontSize = 14.sp)
        )
    }
}

@OptIn(FlowPreview::class)
@Composable
internal fun SearchTextField(
    newMovieHdRezkaViewModel: NewMovieHdRezkaViewModel = koinViewModel()
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val textChangeFlow = remember { MutableStateFlow("") }

    LaunchedEffect(textChangeFlow) {
        textChangeFlow
            .debounce(300)
            .collectLatest {
                if (textState.value.text.isEmpty())
                    return@collectLatest

                newMovieHdRezkaViewModel.search(
                    searchText = textState.value.text
                )
            }
    }

    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            textChangeFlow.value = it.text
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = horizontalPadding),
        colors = defaultTextFieldStyle(),
        maxLines = 1,
        singleLine = true,
    )
}

@Composable
internal fun ListWithResultSearch(
    newMovieHdRezkaViewModel: NewMovieHdRezkaViewModel = koinViewModel(),
    routing: NewMovieHdRezkaRouting
) {
    val list by newMovieHdRezkaViewModel.searchList.observeAsState()

    list?.let { notNullList ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 26.dp)
        ) {
            items(
                items = notNullList,
                key = { it.url }
            ) {
                SearchItem(
                    itemSearch = it,
                    onClickItem = {
                        newMovieHdRezkaViewModel.createMovie(
                            CreateMovieHdRezka(
                                link = it.url,
                                name = it.name,
                                addName = it.addName,
                                type = it.type
                            )
                        )
                        routing.back()
                    }
                )
            }
        }
    } ?: EmptyList()

}

@Composable
internal fun SearchItem(
    itemSearch: ItemSearch,
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
        itemSearch.getType()?.let {
            BoxTypeMovieByType(it)
        }

        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 10.dp)
        ) {
            Text(
                text = itemSearch.name,
                style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start, fontSize = 16.sp)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = itemSearch.addName,
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
                text = "${itemSearch.rating}",
                style = secondTextStyleDuet().copy(color = DuetTheme.colors.backgroundColor)
            )
        }
    }
}

@Composable
internal fun EmptyList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(65.dp),
            painter = painterResource(inc.generics.presentation.R.drawable.ic_link),
            contentDescription = "Empty List",
            tint = DuetTheme.colors.textColor
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Пока молчит",
            style = boldTextStyleDuet().copy(fontSize = 18.sp)
        )
    }
}

@Composable
internal fun BoxTypeMovieByType(typeMovie: TypeMovie) {
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

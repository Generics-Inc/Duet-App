package inc.generics.movie_in_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.generics.presentation.components.TitleTopAppBarDuet

@Composable
fun MovieInDetailScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleTopAppBarDuet(
                text = "Запись подробно",
                onClickNav = {}
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
            PosterHead()
            MovieViewed()
            Description()
            PartsList()
            DeleteMovie()
        }
    }
}

val horizontalPadding = 16.dp

@Composable
fun PosterHead() {
    Row() {

    }
}

@Composable
fun MovieViewed() {

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


package inc.generics.movie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import inc.generics.movie.routing.MovieRouting
import inc.generics.movie.ui.CommonMovieItem
import inc.generics.movie.ui.HeaderList
import inc.generics.movie.ui.SetupActionItemBottomSheet
import inc.generics.movie.ui.SetupAddNewMovieBottomSheet
import inc.generics.movie.vm.ActionItemBottomSheetViewModel
import inc.generics.movie.vm.AddNewMovieBottomSheetViewModel
import inc.generics.movie.vm.MovieFilterViewModel
import inc.generics.movie.vm.MovieViewModel
import inc.generics.movie.vm.StatusScreen
import inc.generics.movie.vm.models.StateBottomSheetViewModel
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(
    routing: MovieRouting,
    viewModel: MovieViewModel = koinViewModel(),
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel(),
    viewModelAddNewBottomSheet: AddNewMovieBottomSheetViewModel = koinViewModel(),
    viewModelFilter: MovieFilterViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAllMovies()
    }

    val status by viewModel.statusScreen.observeAsState(StatusScreen.NONE)
    val listOfMovie by viewModel.listOfMovie.observeAsState(emptyList())

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = status == StatusScreen.LOAD
    )

    val filterText by viewModelFilter.textFilter.observeAsState("")

    val statusBottomSheet by viewModelBottomSheetViewModel
        .state.observeAsState(StateBottomSheetViewModel.DISMISS)
    val stateBottomSheetAddNew by viewModelAddNewBottomSheet.state
        .observeAsState(StateBottomSheetViewModel.DISMISS)

    if (statusBottomSheet == StateBottomSheetViewModel.SHOW) {
        SetupActionItemBottomSheet()
    }
    if (stateBottomSheetAddNew == StateBottomSheetViewModel.SHOW) {
        SetupAddNewMovieBottomSheet(routing = routing)
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
                val filteredList = listOfMovie.filter {
                    if (filterText.isEmpty())
                        true
                    else
                        (it.createdMovie?.name ?: it.taskCreate?.name ?: "")
                            .contains(filterText, ignoreCase = true)
                }

                items(
                    items = filteredList,
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
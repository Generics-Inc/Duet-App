package inc.generics.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultDialogTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys
import inc.generics.requests.routing.RequestsRouting
import org.koin.androidx.compose.koinViewModel

@Composable
fun RequestsScreen(
    routing: RequestsRouting,
    viewModel: RequestsViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllRequests()
    }

    val hasAcceptRequest by viewModel.hasAcceptRequest.observeAsState(false)
    if (hasAcceptRequest) {
        LaunchedEffect(key1 = Unit) {
            routing.toMain()
        }
    }

    Scaffold(
        topBar = {
            TitleTopAppBarDuet(text = DuetTheme.localization[StringsKeys.REQUESTS], onClickNav = {
                routing.goBack()
            })
        }
    ) {
        ListOfRequests(paddingValues = it)
    }
}

@Composable
fun ListOfRequests(paddingValues: PaddingValues, viewModel: RequestsViewModel = koinViewModel()) {
    val statusLoading by viewModel.statusLoading.observeAsState()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = statusLoading == StatusLoading.LOADING
    )
    val listOfRequestUi by viewModel.listOfRequest.observeAsState(emptyList())

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { viewModel.getAllRequests() },
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
            modifier = Modifier
                .background(DuetTheme.colors.backgroundColor)
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            if (listOfRequestUi.isEmpty()) {
                item {
                    EmptyListOfRequests(paddingValues)
                }
            } else {
                listOfRequestUi.map { requestUi ->
                    item {
                        RequestItem(
                            name = requestUi.name,
                            photoUrl = requestUi.photoUrl,
                            onAccept = { viewModel.acceptRequest(requestUi.id) },
                            onCancel = { viewModel.cancelRequest(requestUi.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyListOfRequests(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = DuetTheme.localization[StringsKeys.EMPTY_LIST], style = defaultTextStyleDuet())

        Text(
            text = DuetTheme.localization[StringsKeys.SWIPE_REFRESH_REQUESTS],
            style = defaultTextStyleDuet().copy(fontSize = 14.sp),
            modifier = Modifier.padding(top = 8.dp)
        )

        Icon(
            painter = painterResource(id = inc.generics.presentation.R.drawable.ic_swipe),
            contentDescription = "empty list of requests to group",
            tint = DuetTheme.colors.mainColor,
            modifier = Modifier.padding(10.dp).size(100.dp),
        )
    }
}

@Composable
fun RequestItem(
    name: String,
    photoUrl: String?,
    onAccept: () -> Unit,
    onCancel: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DuetAsyncImage(
                painterIconIfNotImg = painterResource(
                    id = inc.generics.presentation.R.drawable.ic_load_img
                ),
                imgUrl = photoUrl
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    style = defaultTextStyleDuet().copy(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedButtonDuet(
                        onClick = onCancel::invoke,
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        hasBorder = false,
                        hasElevation = false
                    ) {
                        Text(
                            text = DuetTheme.localization[StringsKeys.CANCEL],
                            style = defaultDialogTextStyleDuet().copy(
                                color = DuetTheme.colors.errorColor,
                                fontSize = 12.sp
                            )
                        )
                    }

                    OutlinedButtonDuet(
                        onClick = onAccept::invoke,
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        hasBorder = false,
                        hasElevation = false
                    ) {
                        Text(
                            text = DuetTheme.localization[StringsKeys.ACCEPT],
                            style = defaultDialogTextStyleDuet().copy(
                                color = DuetTheme.colors.successColor,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = DuetTheme.colors.backgroundColor,
        shadowElevation = elevation
    ) {
        content()
    }
}
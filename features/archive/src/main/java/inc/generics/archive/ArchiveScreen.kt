package inc.generics.archive

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import inc.generics.archive.routing.ArchiveRouting
import inc.generics.archive.vm.ActionStatus
import inc.generics.archive.vm.ArchiveViewModel
import inc.generics.archive.vm.ItemArchiveBottomSheetViewModel
import inc.generics.archive.vm.LoadStatus
import inc.generics.archive_data.models.ArchiveItem
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.MultipleEventsCutter
import inc.generics.presentation.utils.get
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArchiveScreen(
    routing: ArchiveRouting,
    viewModel: ArchiveViewModel = koinViewModel(),
) {
    val actionStatus by viewModel.statusAction.observeAsState(ActionStatus.NONE)
    if (actionStatus == ActionStatus.SUCCESS_REVERT) {
        LaunchedEffect(Unit) {
            routing.routToMain()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getArchive()
    }

    val listOfArchive by viewModel.listOfArchive.observeAsState(emptyList())
    val status by viewModel.statusLoadingList.observeAsState(LoadStatus.NONE)
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = status == LoadStatus.LOAD
    )
    val listState = rememberLazyListState()
    val isAtTheEndOfList by remember(listState) {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
                    && listState.layoutInfo.visibleItemsInfo.size != listState.layoutInfo.totalItemsCount
        }
    }

    SetupArchiveBottomSheet()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleTopAppBarDuet(
                text = "Корзина",
                onClickNav = { routing.back() }
            )
        }
    ) { scaffoldPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.getArchive() },
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = DuetTheme.colors.backgroundColor,
                    contentColor = DuetTheme.colors.mainColor
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .fillMaxSize()
                    .background(DuetTheme.colors.backgroundColor)
            ) {
                LazyColumn(
                    state = listState,
                ) {
                    items(
                        items = listOfArchive,
                        key = { it.id }
                    ) { archive ->
                        ItemArchiveUi(
                            archiveData = archive,
                            modifier = Modifier
                                .animateItemPlacement()
                        )
                    }
                }

                AnimatedVisibility(
                    modifier = Modifier,
                    visible = isAtTheEndOfList,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    EndText()
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ItemArchiveUi(
    modifier: Modifier,
    archiveData: ArchiveItem,
    viewModelBottomSheet: ItemArchiveBottomSheetViewModel = koinViewModel()
) {
    val multipleEventsCutter by remember {
        mutableStateOf(MultipleEventsCutter.get())
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 2.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    multipleEventsCutter.processEvent {
                        viewModelBottomSheet.show(archiveData)
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (archiveData.dayBeforeDeleted > 5) {
                DuetAsyncImage(
                    roundedDp = 8.dp,
                    painterIconIfNotImg = painterResource(R.drawable.ic_group),
                    imgUrl = archiveData.photoUrl,
                    colorIcon = DuetTheme.colors.textColor
                )
            } else {
                DuetAsyncImage(
                    roundedDp = 8.dp,
                    painterIconIfNotImg = painterResource(R.drawable.ic_group),
                    imgUrl = archiveData.photoUrl,
                    shadowColor = DuetTheme.colors.errorColor,
                    colorIcon = DuetTheme.colors.textColor
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = archiveData.name,
                    style = defaultTextStyleDuet().copy(
                        textAlign = TextAlign.Start
                    )
                )

                IconAndText(
                    modifier = Modifier.padding(top = 10.dp),
                    color = if (archiveData.dayBeforeDeleted > 5) {
                        DuetTheme.colors.mainColor
                    } else {
                        DuetTheme.colors.errorColor
                    },
                    text = String.format("конеце истории через %dд.", archiveData.dayBeforeDeleted)

                )
            }

            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(R.drawable.ic_arrow_forward),
                tint = DuetTheme.colors.thirdColor,
                contentDescription = "arrow icon"
            )
        }

        CustomDivider()
    }
}

@Composable
fun IconAndText(
    modifier: Modifier = Modifier,
    color: Color,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_heart_broke),
            tint = color,
            contentDescription = "icon",
            modifier = Modifier.size(12.dp)
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            style = secondTextStyleDuet().copy(
                color = color
            )
        )
    }
}

@Composable
fun EndText() {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(horizontal = 22.dp)
            .fillMaxWidth()
            .background(DuetTheme.colors.mainColor.copy(alpha = 0.8f), RoundedCornerShape(10.dp))
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.ic_bulb),
            tint = DuetTheme.colors.backgroundColor,
            contentDescription = ""
        )

        Text(
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
            text = "Создавай новые отношения \n" +
                    "и разрушай их, чтобы\n" +
                    "пополнить список",
            style = defaultTextStyleDuet().copy(
                color = DuetTheme.colors.backgroundColor,
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun CustomDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 36.dp, vertical = 6.dp).clip(
            RoundedCornerShape(8.dp)
        ),
        color = DuetTheme.colors.thirdColor,
        thickness = 1.dp
    )
}

package inc.generics.group

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.group.routing.GroupRouting
import inc.generics.group.vm.GroupViewModel
import inc.generics.group.vm.ScreenStatus
import inc.generics.group_data.models.Group
import inc.generics.group_data.models.Partner
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.boldTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun GroupScreen(
    routing: GroupRouting,
    viewModel: GroupViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getGroup()
    }

    val statusScreen by viewModel.screenStatus.observeAsState(ScreenStatus.NONE)
    LaunchedEffect(statusScreen) {
        when(statusScreen) {
            ScreenStatus.NONE -> Unit
            ScreenStatus.NETWORK_ERROR -> Unit
            ScreenStatus.ON_LEAVE -> {
                routing.routToMain()
            }
        }
    }

    Content(routing = routing)
}

internal val horizontalPadding: Dp = 20.dp

@Composable
internal fun Content(
    modifier: Modifier = Modifier,
    routing: GroupRouting,
    viewModel: GroupViewModel = koinViewModel()
) {
    val dataScreen by viewModel.dataScreen.observeAsState()

    Scaffold(
        modifier.fillMaxWidth().background(DuetTheme.colors.backgroundColor),
        topBar = {
            TitleTopAppBarDuet(
                text = "Группа",
                onClickNav = {
                    routing.back()
                }
            )
        }
    ) { paddingValues ->
        val modifierForContent = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(DuetTheme.colors.backgroundColor)

        dataScreen?.let {
            NotNullContent(
                modifier = modifierForContent,
                group = it
            )
        } ?: Loading(
            modifier = modifierForContent
        )
    }
}

@Composable
internal fun Loading(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(30.dp),
            color = DuetTheme.colors.secondColor
        )
    }
}

@SuppressLint("ReturnFromAwaitPointerEventScope")
@Composable
internal fun NotNullContent(
    modifier: Modifier = Modifier,
    group: Group
) {
    val isShowBottomSheet = remember {
        mutableStateOf(false)
    }
    SetupBottomSheetWithActions(
        stateIsShow = isShowBottomSheet
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DuetAsyncImage(
                imgUrl = group.photoUrl,
                painterIconIfNotImg = painterResource(R.drawable.ic_group),
                colorIcon = DuetTheme.colors.textColor,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = horizontalPadding)
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        color = DuetTheme.colors.backgroundColor, shape = RoundedCornerShape(8.dp)
                    )
            )

            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = horizontalPadding)
            ) {
                Text(
                    text = group.name,
                    style = boldTextStyleDuet(),
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_action),
                    tint = DuetTheme.colors.thirdColor,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(24.dp)
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            isShowBottomSheet.value = true
                        }
                        .padding(4.dp),
                    contentDescription = ""
                )
            }

            group.partner?.let {
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = horizontalPadding)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_like),
                        tint = DuetTheme.colors.textColor,
                        contentDescription = "icon text"
                    )

                    Text(
                        text = "${group.dayTogether} Дней рука об руку",
                        style = secondTextStyleDuet().copy(color = DuetTheme.colors.textColor),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }


            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding)
                    .padding(top = 8.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                color = DuetTheme.colors.secondColor,
                thickness = 1.5.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                ItemStatistic(
                    iconId = R.drawable.ic_movie,
                    statisticsCount = group.statistic.moviesCount,
                    modifier = Modifier.weight(1f),
                    text = "Кино"
                )
                ItemStatistic(
                    iconId = R.drawable.ic_calendar,
                    statisticsCount = group.statistic.moviesCount,
                    modifier = Modifier.weight(1f),
                    text = "Календарь"
                )
                ItemStatistic(
                    iconId = R.drawable.ic_kt,
                    statisticsCount = group.statistic.moviesCount,
                    modifier = Modifier.weight(1f),
                    text = "Кухня"
                )
            }

            group.partner?.let {
                ParentInfo(
                    modifier = Modifier
                        .padding(top = 22.dp)
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            Log.d("CLICK", "Row")
                        },
                    partner = it
                )
            } ?: WithoutPartner(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
            )
        }

        CreatedAt(
            modifier = Modifier.padding(vertical = 16.dp).align(Alignment.BottomCenter),
            date = group.createdAt
        )
    }

}

@Composable
internal fun WithoutPartner(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = DuetTheme.localization[StringsKeys.NO_PARTNER],
        style = boldTextStyleDuet().copy(
            fontSize = 18.sp
        )
    )
}

@Composable
internal fun ParentInfo(
    modifier: Modifier = Modifier,
    partner: Partner
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DuetAsyncImage(
            painterIconIfNotImg = painterResource(R.drawable.ic_profile),
            shadowElevation = 4.dp,
            colorIcon = DuetTheme.colors.thirdColor,
            imgUrl = partner.photoUrl
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = partner.name, style = defaultTextStyleDuet())
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "Партнер",
                style = secondTextStyleDuet()
            )
        }

        Icon(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    Log.d("CLICK", "Icon")
                }
                .padding(4.dp),
            painter = painterResource(R.drawable.ic_delete),
            tint = DuetTheme.colors.errorColor,
            contentDescription = "delete icon",
        )
    }
}

@Composable
internal fun ItemStatistic(
    iconId: Int,
    modifier: Modifier = Modifier,
    statisticsCount: Int,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(iconId),
            tint = DuetTheme.colors.secondColor,
            contentDescription = "icon",
            modifier = Modifier.size(40.dp)
        )

        Column(
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Text(
                "$statisticsCount", style = defaultTextStyleDuet().copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
            Text(
                text,
                style = defaultTextStyleDuet().copy(
                    color = DuetTheme.colors.textColor.copy(alpha = 0.6f),
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
internal fun CreatedAt(
    modifier: Modifier = Modifier,
    date: Date
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(R.drawable.ic_star),
            tint = DuetTheme.colors.mainColor,
            contentDescription = "created at icon"
        )

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Созданно",
            style = defaultTextStyleDuet().copy(fontSize = 14.sp)
        )

        val formattedDate = SimpleDateFormat("MM.dd.yyyy").format(date)

        Text(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = formattedDate,
            style = defaultTextStyleDuet().copy(fontWeight = FontWeight.Bold)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SetupBottomSheetWithActions(
    viewModel: GroupViewModel = koinViewModel(),
    stateIsShow: MutableState<Boolean>
) {
    if (stateIsShow.value) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {
                stateIsShow.value = false
            },
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
            containerColor = DuetTheme.colors.backgroundColor,
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = 22.dp
                    )
            ) {
                Buttons(
                    modifier = Modifier.fillMaxWidth(),
                    onClickDelete = {
                        stateIsShow.value = false
                        viewModel.leaveGroup()
                    },
                    onClickSaveHistory = {},
                    onClickEdit = {}
                )

                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    text = "Выбери действие для группы",
                    style = secondTextStyleDuet()
                )
            }
        }
    }

}

@Composable
internal fun Buttons(
    modifier: Modifier = Modifier,
    onClickDelete: () -> Unit,
    onClickSaveHistory: () -> Unit,
    onClickEdit: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButtonDuet(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onClickSaveHistory()
            },
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = "Скачать LoveStory",
                style = defaultTextStyleForButtonDuet()
            )
        }

        FilledTonalButtonDuet(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                      onClickDelete()
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
}
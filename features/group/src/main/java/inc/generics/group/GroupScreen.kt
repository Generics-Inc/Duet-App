package inc.generics.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import inc.generics.group_data.models.Group
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.boldTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupScreen(
    routing: GroupRouting,
    viewModel: GroupViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getGroup()
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

@Composable
internal fun NotNullContent(
    modifier: Modifier = Modifier,
    group: Group
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DuetAsyncImage(
            imgUrl = group.photoUrl,
            painterIconIfNotImg = painterResource(R.drawable.ic_group),
            colorIcon = DuetTheme.colors.textColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = 16.dp)
                .height(300.dp)
                .background(
                    color = DuetTheme.colors.backgroundColor, shape = RoundedCornerShape(8.dp)
                )
        )

        Text(
            text = group.name,
            style = boldTextStyleDuet(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = horizontalPadding)
        )

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

            group.partner?.let {
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
package inc.generics.archive

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.archive.vm.ArchiveViewModel
import inc.generics.archive.vm.ItemArchiveBottomSheetViewModel
import inc.generics.archive_data.models.ArchiveItem
import inc.generics.presentation.R
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys
import org.koin.androidx.compose.koinViewModel

@SuppressLint("DefaultLocale")
@Composable
internal fun SetupArchiveBottomSheet(
    viewModel: ArchiveViewModel = koinViewModel(),
    viewModelBottomSheet: ItemArchiveBottomSheetViewModel = koinViewModel()
) {
    val isShow by viewModelBottomSheet.isShow.observeAsState(false)
    val dataBottomSheet by viewModelBottomSheet.data.observeAsState(null)

    if (isShow) {
        dataBottomSheet?.let {
            ContentBottomSheet(dataBottomSheet = it)
        }
    }

}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentBottomSheet(
    viewModel: ArchiveViewModel = koinViewModel(),
    viewModelBottomSheet: ItemArchiveBottomSheetViewModel = koinViewModel(),
    dataBottomSheet: ArchiveItem
) {
    ModalBottomSheet(
        onDismissRequest = {
            viewModelBottomSheet.hide()
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
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (dataBottomSheet.dayBeforeDeleted > 5) {
                    DuetAsyncImage(
                        roundedDp = 8.dp,
                        painterIconIfNotImg = painterResource(R.drawable.ic_group),
                        imgUrl = dataBottomSheet.photoUrl,
                        colorIcon = DuetTheme.colors.textColor
                    )
                } else {
                    DuetAsyncImage(
                        roundedDp = 8.dp,
                        painterIconIfNotImg = painterResource(R.drawable.ic_group),
                        imgUrl = dataBottomSheet.photoUrl,
                        shadowColor = DuetTheme.colors.errorColor,
                        colorIcon = DuetTheme.colors.textColor
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = dataBottomSheet.name,
                        style = defaultTextStyleDuet().copy(
                            textAlign = TextAlign.Start
                        )
                    )

                    IconAndText(
                        modifier = Modifier.padding(top = 10.dp),
                        color = if (dataBottomSheet.dayBeforeDeleted > 5) {
                            DuetTheme.colors.mainColor
                        } else {
                            DuetTheme.colors.errorColor
                        },
                        text = String.format(
                            "конеце истории через %dд.",
                            dataBottomSheet.dayBeforeDeleted
                        )

                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilledTonalButtonDuet(
                    modifier = Modifier.weight(1f),
                    color = DuetTheme.colors.errorColor,
                    onClick = {
                        viewModel.deleteWithUpdateUiData(dataBottomSheet.id)
                        viewModelBottomSheet.hide()
                    }
                ) {
                    Text(text = "Удалить", style = defaultTextStyleForButtonDuet().copy(fontSize = 14.sp))
                }

                FilledTonalButtonDuet(
                    modifier = Modifier.weight(1f),
                    color = DuetTheme.colors.successColor,
                    onClick = {
                        viewModel.revertWithUpdateUiData(dataBottomSheet.id)
                        viewModelBottomSheet.hide()
                    }
                ) {
                    Text(text = "Восстановить", style = defaultTextStyleForButtonDuet().copy(fontSize = 14.sp))
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 8.dp).clip(
                    RoundedCornerShape(8.dp)
                ),
                color = DuetTheme.colors.secondColor,
                thickness = 2.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                ItemStatistic(
                    iconId = R.drawable.ic_movie,
                    statisticsCount = dataBottomSheet.moviesCount,
                    modifier = Modifier.weight(1f),
                    text = "Кино"
                )
                ItemStatistic(
                    iconId = R.drawable.ic_calendar,
                    statisticsCount = dataBottomSheet.eventsCount,
                    modifier = Modifier.weight(1f),
                    text = "Календарь"
                )
                ItemStatistic(
                    iconId = R.drawable.ic_kt,
                    statisticsCount = dataBottomSheet.kitchenCount,
                    modifier = Modifier.weight(1f),
                    text = "Кухня"
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (dataBottomSheet.partner != null) {
                    DuetAsyncImage(
                        painterIconIfNotImg = painterResource(R.drawable.ic_profile),
                        colorIcon = DuetTheme.colors.textColor,
                        imgUrl = dataBottomSheet.partner!!.photoUrl,
                        size = 120.dp
                    )
                    Text(
                        text = dataBottomSheet.partner!!.name,
                        style = defaultTextStyleDuet()
                    )
                } else {
                    Text(
                        text = DuetTheme.localization[StringsKeys.NO_PARTNER],
                        style = defaultTextStyleDuet().copy(fontWeight = FontWeight.Bold)
                    )
                }

                OutlinedButtonDuet(
                    modifier = Modifier.padding(top = 22.dp).height(50.dp),
                    onClick = {}
                ) {
                    Text(
                        text = "Скачать историю",
                        style = defaultTextStyleForButtonDuet()
                    )
                }
            }
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
            Text("$statisticsCount", style = defaultTextStyleDuet().copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ))
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
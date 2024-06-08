package inc.generics.archive

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import inc.generics.archive.models.ArchiveItemDataUi
import inc.generics.archive.routing.ArchiveRouting
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme

@Composable
fun ArchiveScreen(
    routing: ArchiveRouting
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleTopAppBarDuet(
                text = "Корзина",
                onClickNav = { routing.back() }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(DuetTheme.colors.backgroundColor)
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp),
        ) {
            item {
                ItemArchiveUi(
                    archiveData = ArchiveItemDataUi(
                        name = "Группа 1",
                        photoUrl = null,
                        dayBeforeDeleted = 10
                    )
                )
                CustomDivider()
            }
            item {
                ItemArchiveUi(
                    archiveData = ArchiveItemDataUi(
                        name = "Группа 2",
                        photoUrl = null,
                        dayBeforeDeleted = 1
                    )
                )
                CustomDivider()
            }
            item {
                ItemArchiveUi(
                    archiveData = ArchiveItemDataUi(
                        name = "Группа 3",
                        photoUrl = null,
                        dayBeforeDeleted = 15
                    )
                )
                CustomDivider()
            }
            item {
                ItemArchiveUi(
                    archiveData = ArchiveItemDataUi(
                        name = "Группа 4",
                        photoUrl = null,
                        dayBeforeDeleted = 5
                    )
                )
                CustomDivider()
            }
            item {
                ItemArchiveUi(
                    archiveData = ArchiveItemDataUi(
                        name = "Группа 5",
                        photoUrl = null,
                        dayBeforeDeleted = 2
                    )
                )
                CustomDivider()
            }
        }
    }
}

@Composable
fun ItemArchiveUi(
    archiveData: ArchiveItemDataUi
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (archiveData.dayBeforeDeleted > 5) {
            DuetAsyncImage(
                roundedDp = 8.dp,
                painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_group),
                imgUrl = archiveData.photoUrl
            )
        } else {
            DuetAsyncImage(
                roundedDp = 8.dp,
                painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_group),
                imgUrl = archiveData.photoUrl,
                shadowColor = DuetTheme.colors.errorColor,
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = archiveData.name,
                style = defaultTextStyleDuet()
            )

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = String.format("до удаления %dд.", archiveData.dayBeforeDeleted),
                style = if (archiveData.dayBeforeDeleted > 5) {
                    secondTextStyleDuet()
                } else {
                    secondTextStyleDuet().copy(
                        color = DuetTheme.colors.errorColor
                    )
                }
            )
        }

        Icon(
            modifier = Modifier.padding(end = 8.dp),
            painter = painterResource(inc.generics.presentation.R.drawable.ic_arrow_forward),
            tint = DuetTheme.colors.thirdColor,
            contentDescription = "arrow icon"
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
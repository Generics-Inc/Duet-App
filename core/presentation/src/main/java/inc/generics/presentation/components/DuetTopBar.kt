package inc.generics.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.CutterType
import inc.generics.presentation.utils.EventsCutter
import inc.generics.presentation.utils.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBarDuet(
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS,
    showProfileIcon: Boolean = true,
    clickOnProfile: () -> Unit = {}
) {
    val eventsCutter by remember {
        mutableStateOf(EventsCutter.get(cutterType))
    }

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    painterResource(R.drawable.ic_logo_duet),
                    tint = DuetTheme.colors.backgroundColor,
                    contentDescription = "logo",
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                if (showProfileIcon) {
                    Icon(
                        painterResource(R.drawable.ic_profile),
                        tint = DuetTheme.colors.backgroundColor,
                        contentDescription = "logo",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                eventsCutter?.processEvent { clickOnProfile() }
                                    ?: clickOnProfile()
                            }
                            .padding(16.dp)
                    )
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = DuetTheme.colors.mainColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTopAppBarDuet(
    text: String,
    cutterType: CutterType = CutterType.MULTIPLE_EVENTS,
    onClickNav: () -> Unit = {}
) {
    val eventsCutter by remember {
        mutableStateOf(EventsCutter.get(cutterType))
    }

    TopAppBar(
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_nav_topbar_back),
                tint = DuetTheme.colors.backgroundColor,
                contentDescription = "navigation icon",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        eventsCutter?.processEvent { onClickNav() } ?: onClickNav()
                    }
                    .padding(16.dp)
            )
        },
        title = {
            Row(horizontalArrangement = Arrangement.Center) {
                Icon(
                    painterResource(R.drawable.ic_logo_duet_mini),
                    tint = DuetTheme.colors.backgroundColor,
                    contentDescription = "logo",
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = text,
                    style = titleContrastTestStyleDuet(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 16.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = DuetTheme.colors.mainColor
        )
    )
}
package inc.generics.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBarDuet() {
    TopAppBar(
        title = {
            Icon(
                painterResource(R.drawable.ic_logo_duet),
                tint = DuetTheme.colors.backgroundColor,
                contentDescription = "logo"
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = DuetTheme.colors.mainColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTopAppBarDuet(text: String, onClickNav: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_nav_topbar_back),
                tint = DuetTheme.colors.backgroundColor,
                contentDescription = "navigation icon",
                modifier = Modifier.clickable { onClickNav() }.padding(16.dp)
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
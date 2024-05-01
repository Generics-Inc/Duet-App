package inc.generics.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
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
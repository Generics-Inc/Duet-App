package inc.generics.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.profile.routing.ProfileRouting

@Composable
fun ProfileScreen(
    routing: ProfileRouting
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TitleTopAppBarDuet(
            text = "Профиль",
            onClickNav = {
                routing.toBack()
            }
        ) }
    ) {
        ScreenContent(
            paddingValues = it
        )
    }
}
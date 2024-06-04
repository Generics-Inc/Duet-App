package inc.generics.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import inc.generics.presentation.components.DefaultTopAppBarDuet

@Composable
fun ProfileScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { DefaultTopAppBarDuet() }
    ) {
        ScreenContent(
            paddingValues = it
        )
    }
}
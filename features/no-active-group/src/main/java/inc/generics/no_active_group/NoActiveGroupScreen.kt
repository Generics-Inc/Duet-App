package inc.generics.no_active_group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import inc.generics.no_active_group.models.NoActiveGroupUiData
import inc.generics.no_active_group.routing.NoActiveGroupRouting
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.theme.DuetTheme

@Composable
fun NoActiveGroupScreen(
    routing: NoActiveGroupRouting,
    noActiveGroupUiData: NoActiveGroupUiData
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DuetTheme.colors.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserWithoutGroup(routing = routing, noActiveGroupUiData = noActiveGroupUiData)
        }
    }
}
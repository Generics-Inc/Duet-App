package inc.generics.duet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import inc.generics.duet.navigation.screens.ExternalScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun Main(mainViewModel: MainViewModel = koinViewModel(), mainNavController: NavHostController) {
    val statusGroup by mainViewModel.statusGroup.observeAsState(StatusGroup.NONE)

    LaunchedEffect(Unit) {
        mainViewModel.checkGroupStatus()
    }

    LaunchedEffect(key1 = statusGroup) {
        when(statusGroup) {
            StatusGroup.NOT_AUTHORIZE ->
                mainNavController.navigate(ExternalScreens.Authorization.route)
            StatusGroup.NOT_ACTIVE_GROUP ->
                mainNavController.navigate(ExternalScreens.NoActiveGroup.route)
            StatusGroup.IN_GROUP__NO_PARTNER -> {}
            StatusGroup.IN_GROUP__PARTNER_LEAVED -> {}
            StatusGroup.ACTIVE_GROUP -> {}
            StatusGroup.NONE -> {
                // mb loading screen
            }
        }
    }
}
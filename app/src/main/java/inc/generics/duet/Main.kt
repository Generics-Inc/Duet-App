package inc.generics.duet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateWithData
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.no_active_group.models.NoActiveGroupUiData
import org.koin.androidx.compose.koinViewModel

@Composable
fun Main(mainViewModel: MainViewModel = koinViewModel(), mainNavController: NavHostController) {
    val statusGroup by mainViewModel.statusGroup.observeAsState(StatusGroup.None)

    LaunchedEffect(Unit) {
        mainViewModel.checkGroupStatus()
    }

    LaunchedEffect(key1 = statusGroup) {
        when(statusGroup) {
            is StatusGroup.NotAuthorize ->
                mainNavController.navigate(ExternalScreens.Authorization.route)
            is StatusGroup.NotActiveGroup -> {
                mainNavController.navigateWithData(
                    ExternalScreens.NoActiveGroup.route,
                    Pair(
                        ExternalScreens.NoActiveGroup.dataKey,
                        NoActiveGroupUiData((statusGroup as StatusGroup.NotActiveGroup).hashArchive)
                    )
                )
            }
            is StatusGroup.InGroupNoPartner -> {
                // экран редактирования группы
            }
            is StatusGroup.InGroupPartnerLeaved -> {
                // переход с инфой по ситатусу партнера
                // GROUP_IN_ARCHIVE or LEAVED and isMainInGroup
            }
            StatusGroup.ActiveGroup -> {
                // доступ к контенту приложения
            }
            StatusGroup.None -> {
                // mb loading screen
            }
        }
    }
}
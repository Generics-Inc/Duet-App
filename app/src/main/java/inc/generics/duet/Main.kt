package inc.generics.duet

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.navigateWithDataInclusive
import inc.generics.duet.navigation.screens.ExternalScreens
import org.koin.androidx.compose.koinViewModel

@SuppressLint("RestrictedApi")
@Composable
fun Main(mainViewModel: MainViewModel = koinViewModel(), mainNavController: NavHostController) {
    val statusGroup by mainViewModel.statusGroup.observeAsState(StatusGroup.None)

    LaunchedEffect(Unit) {
        Log.d("checkGroupStatus", "checkGroupStatus")
        mainViewModel.checkGroupStatus()
    }

    LaunchedEffect(key1 = statusGroup) {
        when(statusGroup) {
            is StatusGroup.NotAuthorize ->
                mainNavController.navigateInclusive(ExternalScreens.Authorization.route)
            is StatusGroup.NotActiveGroup -> {
                mainNavController.navigateWithDataInclusive(
                    ExternalScreens.NoActiveGroup.route,
                    (statusGroup as StatusGroup.NotActiveGroup).hashArchive.toString()
                )
            }
            is StatusGroup.InGroupNoPartner -> {
                mainNavController.navigateInclusive(ExternalScreens.GroupWithoutPartner.route)
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
package inc.generics.duet

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateReplace
import inc.generics.duet.navigation.navigateReplaceWithData
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.group_left_by_partner.models.StatusGroupLeftByPartner
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.theme.DuetTheme
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
        determineStatusGroup(
            statusGroup = statusGroup,
            onNone = {},
            onNotAuthorize = {
                mainNavController.navigateReplace(
                    ExternalScreens.Authorization.route,
                    ExternalScreens.Main.route
                )
            },
            onNotActiveGroup = { status ->
                mainNavController.navigateReplaceWithData(
                    ExternalScreens.NoActiveGroup.route,
                    ExternalScreens.Main.route,
                    status.hashArchive.toString(),
                )
            },
            onInGroupNoPartner = {
                mainNavController.navigateReplace(
                    ExternalScreens.GroupWithoutPartner.route,
                    ExternalScreens.Main.route
                )
            },
            onInGroupPartnerLeaved = { status ->
                mainNavController.navigateReplaceWithData(
                    routeTo = ExternalScreens.GroupLeftByPartner.route,
                    routeFrom = ExternalScreens.Main.route,
                    Pair(
                        ExternalScreens.GroupLeftByPartner.dataKey, StatusGroupLeftByPartner(
                            status.isMainInGroup,
                            status.isPartnerLeaved
                        )
                    )
                )
            },
            onActiveGroup = {
                mainNavController.navigateReplace(
                    routeTo = ExternalScreens.SubNavigation.route,
                    routeFrom = ExternalScreens.Main.route
                )
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(DuetTheme.colors.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(bottom = 20.dp).size(30.dp),
            color = DuetTheme.colors.secondColor
        )

        //todo: delete button. it is for a test
        DefaultFilledTonalButtonDuet(
            text = "Выйти",
            onClick = {
                mainViewModel.leaveGroup()
            }
        )
    }
}

fun determineStatusGroup(
    statusGroup: StatusGroup,
    onNone: () -> Unit,
    onNotAuthorize: () -> Unit,
    onNotActiveGroup: (status: StatusGroup.NotActiveGroup) -> Unit,
    onInGroupNoPartner: () -> Unit,
    onInGroupPartnerLeaved: (status: StatusGroup.InGroupPartnerLeaved) -> Unit,
    onActiveGroup: () -> Unit
) = when (statusGroup) {
    is StatusGroup.None -> onNone()
    is StatusGroup.NotAuthorize -> onNotAuthorize()
    is StatusGroup.NotActiveGroup -> onNotActiveGroup(statusGroup)
    is StatusGroup.InGroupNoPartner -> onInGroupNoPartner()
    is StatusGroup.InGroupPartnerLeaved -> onInGroupPartnerLeaved(statusGroup)
    is StatusGroup.ActiveGroup -> onActiveGroup()
}
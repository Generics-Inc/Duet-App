package inc.generics.no_active_group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import inc.generics.no_active_group.models.GroupInf
import inc.generics.no_active_group.models.StatusGroup
import inc.generics.no_active_group.routing.NoActiveGroupRouting
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoActiveGroupScreen(
    groupInf: GroupInf,
    routing: NoActiveGroupRouting
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DuetTheme.colors.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (groupInf.userDeletedOrLeaveGroup != StatusGroup.ACTIVE) {
                UserWithoutGroup()
            } else if (groupInf.partnerStatusGroup == null) {
                routing.routToCreateInviteCode()
            } else if (groupInf.partnerStatusGroup != StatusGroup.ACTIVE){
                NoPartnerInGroup(
                    when (groupInf.partnerStatusGroup) {
                        StatusGroup.LEAVE -> true
                        else -> false
                    }
                )
            }
        }
    }
}
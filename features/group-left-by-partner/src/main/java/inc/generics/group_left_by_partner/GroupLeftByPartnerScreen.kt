package inc.generics.group_left_by_partner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.generics.group_left_by_partner.models.StatusGroupLeftByPartner
import inc.generics.group_left_by_partner.routing.GroupLeftByPartnerRouting
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.HeadTestAndIcon
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.CutterType
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupLeftByPartnerScreen(
    status: StatusGroupLeftByPartner,
    routing: GroupLeftByPartnerRouting,
    viewModel: GroupLeftByPartnerViewModel = koinViewModel()
) {
    val stateScreen by viewModel.stateScreen.observeAsState(StateScreen.NONE)
    LaunchedEffect(stateScreen) {
        when(stateScreen) {
            StateScreen.NONE -> Unit
            StateScreen.DELETE_PARTNER -> routing.toMain()
            StateScreen.LEAVE_GROUP -> routing.toMain()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet()
        }
    ) {
        NoPartnerInGroup(
            status = status,
            routing = routing,
            paddingValues = it
        )
    }
}

@Composable
internal fun NoPartnerInGroup(
    status: StatusGroupLeftByPartner,
    routing: GroupLeftByPartnerRouting,
    paddingValues: PaddingValues,
    viewModel: GroupLeftByPartnerViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(DuetTheme.colors.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (status.isPartnerDeleteGroup) {
            HeadTestAndIcon("Ваш партнер\nвышел из группы")
            DefaultOutlinedButtonDuet(
                onClick = {
                    routing.toNewInviteCode()
                },
                cutterType = CutterType.ONLY_ONE_CLICK,
                text = "Новый код",
                modifier = Modifier
                    .padding(top = 65.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
            )
        } else {
            HeadTestAndIcon("Ваш партнер \n" +
                    "поместил группу\n" +
                    "в корзину\n")
        }

        DefaultOutlinedButtonDuet(
            onClick = {  },
            text = "Скачать архив",
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )

        if (status.isMainInGroup && !status.isPartnerDeleteGroup) {
            DefaultFilledTonalButtonDuet(
                onClick = { viewModel.deletePartner() },
                text = "Удалить партнера",
                color = DuetTheme.colors.errorColor,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
            )
        }

        DefaultFilledTonalButtonDuet(
            onClick = { viewModel.leaveGroup() },
            text = "Удалить",
            color = DuetTheme.colors.errorColor,
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )
    }
}
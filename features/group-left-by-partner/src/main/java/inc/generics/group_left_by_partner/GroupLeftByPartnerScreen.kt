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
import inc.generics.group_left_by_partner.view_models.GroupLeftByPartnerDialogViewModel
import inc.generics.group_left_by_partner.view_models.GroupLeftByPartnerViewModel
import inc.generics.group_left_by_partner.view_models.StateDialogs
import inc.generics.group_left_by_partner.view_models.StateScreen
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.DuetAlertDialogRequest
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
        when (stateScreen) {
            StateScreen.NONE -> Unit
            StateScreen.DELETE_PARTNER -> routing.toMain()
            StateScreen.LEAVE_GROUP -> routing.toMain()
        }
    }

    SetupDialogs()

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
    viewModel: GroupLeftByPartnerViewModel = koinViewModel(),
    dialogViewModel: GroupLeftByPartnerDialogViewModel = koinViewModel()
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
            HeadTestAndIcon(
                "Ваш партнер \n" +
                        "поместил группу\n" +
                        "в корзину\n"
            )
        }

        DefaultOutlinedButtonDuet(
            onClick = { },
            text = "Скачать архив",
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )

        if (status.isMainInGroup && !status.isPartnerDeleteGroup) {
            DefaultFilledTonalButtonDuet(
                onClick = { dialogViewModel.tryDeletePartner() },
                text = "Удалить партнера",
                color = DuetTheme.colors.errorColor,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
            )
        }

        DefaultFilledTonalButtonDuet(
            onClick = { dialogViewModel.tryLeaveGroup() },
            text = "Удалить",
            color = DuetTheme.colors.errorColor,
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )
    }
}

@Composable
internal fun SetupDialogs(
    dialogViewModel: GroupLeftByPartnerDialogViewModel = koinViewModel()
) {
    val stateDialogs by dialogViewModel.stateDialogs.observeAsState()

    when (stateDialogs) {
        null -> Unit
        StateDialogs.NO_DIALOG -> Unit
        StateDialogs.TRY_DELETE_PARTNER -> DialogDeletePartner()
        StateDialogs.TRY_LEAVE_GROUP -> DialogLeaveGroup()
    }
}

@Composable
internal fun DialogDeletePartner(
    viewModel: GroupLeftByPartnerViewModel = koinViewModel(),
    dialogViewModel: GroupLeftByPartnerDialogViewModel = koinViewModel()
) {
    DuetAlertDialogRequest(
        "Вы уверены что хотите удалить партнера из группы?",
        onClose = {},
        onAccept = {
            viewModel.deletePartner()
        },
        onDismiss = {
            dialogViewModel.dismissDialog()
        }
    )
}

@Composable
internal fun DialogLeaveGroup(
    viewModel: GroupLeftByPartnerViewModel = koinViewModel(),
    dialogViewModel: GroupLeftByPartnerDialogViewModel = koinViewModel()
) {
    DuetAlertDialogRequest(
        "Вы уверены что хотите выйте из группы? Группа будет перемещена в корзину.",
        onClose = {},
        onAccept = {
            viewModel.leaveGroup()
        },
        onDismiss = {
            dialogViewModel.dismissDialog()
        }
    )
}

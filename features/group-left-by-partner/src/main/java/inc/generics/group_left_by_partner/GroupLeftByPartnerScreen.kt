package inc.generics.group_left_by_partner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys
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
            StateScreen.NEW_INVITE_CODE_IN_PROCESS -> Unit
            StateScreen.NEW_INVITE_CODE_IS_GENERATED -> routing.toMain()
        }
    }

    SetupDialogs()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet(
                clickOnProfile = { routing.toProfile() }
            )
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
    val stateScreen by viewModel.stateScreen.observeAsState(StateScreen.NONE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(DuetTheme.colors.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (status.isPartnerDeleteGroup) {
            HeadTestAndIcon(DuetTheme.localization[StringsKeys.PARTNER_DELETE_GROUP])
            OutlinedButtonDuet(
                onClick = {
                    viewModel.newInviteCode()
                },
                cutterType = CutterType.MULTIPLE_EVENTS,
                enabled = stateScreen != StateScreen.NEW_INVITE_CODE_IN_PROCESS,
                modifier = Modifier
                    .padding(top = 65.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
                    .size(187.dp, 45.dp)
            ) {
                if (stateScreen == StateScreen.NEW_INVITE_CODE_IN_PROCESS) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = DuetTheme.colors.secondColor
                    )
                } else {
                    Text(
                        text = DuetTheme.localization[StringsKeys.NEW_CODE],
                        style = defaultTextStyleForButtonDuet()
                    )
                }
            }
        } else {
            HeadTestAndIcon(
                DuetTheme.localization[StringsKeys.PARTNER_LEAVE]
            )
        }

        DefaultOutlinedButtonDuet(
            onClick = { },
            text = DuetTheme.localization[StringsKeys.SAVE_ARCHIVE],
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )

        if (status.isMainInGroup && !status.isPartnerDeleteGroup) {
            DefaultFilledTonalButtonDuet(
                onClick = { dialogViewModel.tryDeletePartner() },
                text = DuetTheme.localization[StringsKeys.DELETE_PARTNER],
                color = DuetTheme.colors.errorColor,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
            )
        }

        DefaultFilledTonalButtonDuet(
            onClick = { dialogViewModel.tryLeaveGroup() },
            text = DuetTheme.localization[StringsKeys.GET_OUT],
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
        DuetTheme.localization[StringsKeys.REQUEST_DELETE_PARTNER],
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
        DuetTheme.localization[StringsKeys.REQUEST_DELETE_GROUP],
        onClose = {},
        onAccept = {
            viewModel.leaveGroup()
        },
        onDismiss = {
            dialogViewModel.dismissDialog()
        }
    )
}

package inc.generics.join_to_group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import inc.generics.join_to_group.routing.JoinToGroupRouting
import inc.generics.presentation.R
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DuetAlertDialogError
import inc.generics.presentation.components.DuetAlertDialogMessage
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextFieldStyle
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys
import org.koin.androidx.compose.koinViewModel

@Composable
fun JoinToGroupScreen(routing: JoinToGroupRouting) {
    SetupEmptyNameGroupBottomSheet()
    SetupErrorDialog()
    SetupFinishDialog(routing = routing)

    Scaffold(
        topBar = {
            TitleTopAppBarDuet(text = DuetTheme.localization[StringsKeys.JOIN_TO_GROUP], onClickNav = {
                routing.goBack()
            })
        }
    ) {
        Content(it)
    }
}

@Composable
internal fun Content(
    paddingValues: PaddingValues,
    viewModel: JoinToGroupViewModel = koinViewModel()
) {
    var inviteCodeText by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DuetTheme.colors.backgroundColor)
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = DuetTheme.localization[StringsKeys.INPUT_INVITE_CODE_TEXT],
                style = defaultTextStyleDuet(),
                textAlign = TextAlign.Center
            )

            TextField(
                value = inviteCodeText,
                onValueChange = { newText ->
                    inviteCodeText = newText
                },
                label = { Text(text = DuetTheme.localization[StringsKeys.INPUT_INVITE_CODE]) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(16.dp),
                colors = defaultTextFieldStyle(),
            )
        }

        val statusInviteCode by viewModel.statusInviteCode.observeAsState(
            StatusInviteCode.NONE
        )

        DefaultFilledTonalButtonDuet(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp),
            enabled = statusInviteCode != StatusInviteCode.SEND,
            onClick = {
                viewModel.sendInviteCode(inviteCodeText)
            },
            text = DuetTheme.localization[StringsKeys.SEND]
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SetupEmptyNameGroupBottomSheet(viewModel: JoinToGroupViewModel = koinViewModel()) {
    val sheetState = rememberModalBottomSheetState()
    val statusInviteCode by viewModel.statusInviteCode.observeAsState(
        StatusInviteCode.NONE
    )

    if (statusInviteCode == StatusInviteCode.EMPTY) {
        ModalBottomSheet(
            sheetState = sheetState,
            modifier = Modifier.padding(),
            onDismissRequest = {
                viewModel.setNoneToStatusInviteCode()
            },
            containerColor = DuetTheme.colors.backgroundColor,
            scrimColor = DuetTheme.colors.errorColor.copy(alpha = 0.2f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_alert),
                    contentDescription = "error icon",
                    tint = DuetTheme.colors.errorColor
                )

                Text(
                    text = DuetTheme.localization[StringsKeys.EMPTY_INPUT_FIELD_INVITE_CODE],
                    style = defaultTextStyleDuet().copy(color = DuetTheme.colors.errorColor),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }

        }
    }
}

@Composable
fun SetupErrorDialog(viewModel: JoinToGroupViewModel = koinViewModel()) {
    val statusInviteCode by viewModel.statusInviteCode.observeAsState(
        StatusInviteCode.NONE
    )

    if (statusInviteCode == StatusInviteCode.ERROR) {
        DuetAlertDialogError(
            messageText = DuetTheme.localization[StringsKeys.CHECK_INVITE_CODE_OR_TRY_AGAIN],
            onClose = {
                viewModel.setNoneToStatusInviteCode()
            }
        )
    }
}

@Composable
fun SetupFinishDialog(
    viewModel: JoinToGroupViewModel = koinViewModel(),
    routing: JoinToGroupRouting
) {
    val statusInviteCode by viewModel.statusInviteCode.observeAsState(
        StatusInviteCode.NONE
    )

    if (statusInviteCode == StatusInviteCode.FINISH) {
        DuetAlertDialogMessage(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ok),
                    contentDescription = "ok icon",
                    tint = DuetTheme.colors.successColor
                )
            },
            messageText = DuetTheme.localization[StringsKeys.REQUEST_TO_JOIN_IS_SENT_WAIT],
            onClose = {
                viewModel.setNoneToStatusInviteCode()
                routing.goBack()
            }
        )
    }
}

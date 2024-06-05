package inc.generics.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.id.onetap.compose.onetap.sheet.OneTapBottomSheet
import com.vk.id.onetap.compose.onetap.sheet.rememberOneTapBottomSheetState
import inc.generics.authorization.routing.AuthorizationScreenRouting
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.DuetAlertDialogError
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.colors.vkColors
import inc.generics.presentation.theme.localization.StringsKeys
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationViewModel = koinViewModel(),
    router: AuthorizationScreenRouting
) {
    val authStatus by viewModel.authorizationStatus.observeAsState(AuthStatus.NoAuthorized)

    if (authStatus == AuthStatus.Successes) {
        LaunchedEffect(Unit) {
            router.routToMain()
        }
        return
    } else if (authStatus == AuthStatus.Error) {
        DuetAlertDialogError(
            messageText = DuetTheme.localization[StringsKeys.CHECK_INTERNET_OR_TRY_AGAIN],
            onClose = { viewModel.clearStatus() }
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet(
                showProfileIcon = false
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DuetTheme.colors.mainColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                painterResource(id = R.drawable.ic_auth),
                contentDescription = "auth icon",
                tint = DuetTheme.colors.backgroundColor,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = DuetTheme.localization[StringsKeys.AUTH_TEXT],
                style = defaultTextStyleDuet().copy(color = DuetTheme.colors.backgroundColor),
                modifier = Modifier.padding(16.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                VkAuth()
            }

        }
    }
}

@Composable
internal fun VkAuth(viewModel: AuthorizationViewModel = koinViewModel()) {
    val bottomSheetState = rememberOneTapBottomSheetState()
    OneTapBottomSheet(
        state = bottomSheetState,
        onAuth = { token ->
            viewModel.toAuthorizeByVk(accessToke = token.token)
        },
        onFail = {},
        serviceName = "Duet"
    )

    OutlinedButton(
        onClick = { bottomSheetState.show() },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = vkColors.mainColor,
            contentColor = DuetTheme.colors.backgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(8.dp),
        border = null,
        elevation = null
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = inc.generics.presentation.R.drawable.ic_vk_logo),
                contentDescription = "vk logo",
                tint = DuetTheme.colors.backgroundColor
            )
            Text(
                text = DuetTheme.localization[StringsKeys.AUTH_BY_VK],
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.Center
            )
        }

    }
}
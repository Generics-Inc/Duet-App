package inc.generics.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vk.id.onetap.compose.onetap.sheet.OneTapBottomSheet
import com.vk.id.onetap.compose.onetap.sheet.rememberOneTapBottomSheetState
import inc.generics.authorization.routing.AuthorizationScreenRouting
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationViewModel = koinViewModel(),
    router: AuthorizationScreenRouting
) {
    val authStatus by viewModel.authorizationStatus.observeAsState(AuthStatus.NoAuthorized)

    if (authStatus == AuthStatus.Successes) {
        router.routToMain()
        return
    } else if (authStatus == AuthStatus.Error) {
        //show bottomSheet about Error
        return
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            DefaultTopAppBarDuet()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DuetTheme.colors.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                painterResource(id = R.drawable.ic_auth),
                contentDescription = "auth icon",
                tint = DuetTheme.colors.mainColor,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = DuetTheme.localization.getString("authText"),
                style = defaultTextStyleDuet(),
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

    DefaultOutlinedButtonDuet(
        onClick = { bottomSheetState.show() },
        text = DuetTheme.localization.getString("authByVk"),
        modifier = Modifier.padding(24.dp)
    )
}
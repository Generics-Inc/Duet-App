package inc.generics.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DuetAlertDialogRequest
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.RouteButtonModel
import inc.generics.presentation.components.RouteIconButton
import inc.generics.presentation.components.RouteIconButtonAnimated
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.localization.StringsKeys
import inc.generics.profile.routing.ProfileRouting
import inc.generics.profile.view_models.ProfileViewModel
import inc.generics.profile.view_models.TypeAccount
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ScreenContent(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = koinViewModel(),
    routing: ProfileRouting
) {
    val screenState by viewModel.screenState.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInformationAboutProfile()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(DuetTheme.colors.backgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        if (screenState != null) {
            item(key = 0) { UserInfo() }
            item(key = 1) { AccountList() }
            item(key = 2) { GroupInfo() }
        } else {
            item(key = 0) { Loading() }
        }
        item(key = 3) { Settings(routing = routing) }
    }

}

@Composable
internal fun UserInfo(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val screenState by viewModel.screenState.observeAsState()
    screenState?.let { state ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            DuetAsyncImage(
                imgUrl = null,
                painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_profile)
            )

            Column(
                modifier = Modifier.weight(1f).padding(top = 14.dp)
            ) {
                Text(
                    text = state.fullName,
                    style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start),
                    modifier = Modifier.fillMaxWidth()
                )
                state.status?.let {
                    Text(
                        text = it,
                        style = secondTextStyleDuet().copy(textAlign = TextAlign.Start),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }

            }
        }
    }
}

@Composable
internal fun Loading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(30.dp),
            color = DuetTheme.colors.secondColor
        )
    }
}

@Composable
internal fun AccountList(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val screenState by viewModel.screenState.observeAsState()
    screenState?.let { state ->
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
        ) {
            state.accountsList.map {
                item {
                    AccountItem(
                        username = it.username,
                        typeAccount = it.typeAccount
                    )
                }
            }
        }
    }
}

@Composable
internal fun AccountItem(
    username: String,
    typeAccount: TypeAccount
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        //todo: в зависимости от типа аккаунта менять лого + текст
        Icon(
            painter = painterResource(inc.generics.presentation.R.drawable.ic_vk_logo),
            contentDescription = "account icon",
            tint = DuetTheme.colors.secondColor
        )

        Column(
            modifier = Modifier.padding(start = 3.dp)
        ) {
            Text(
                text = username,
                style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start, fontSize = 12.sp),
            )
            Text(
                text = "VK", style = secondTextStyleDuet().copy(
                    color = DuetTheme.colors.secondColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp
                ), modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}

@Composable
internal fun GroupInfo(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val screenState by viewModel.screenState.observeAsState()
    screenState?.let { state ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
        ) {
            Text(
                text = state.groupInfo?.nameGroup
                    ?: DuetTheme.localization[StringsKeys.NO_ACTIVE_GROUP_ONE_LINE],
                style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start, fontSize = 20.sp),
                fontWeight = FontWeight.Bold
            )

            state.groupInfo?.let { groupInfo ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DuetAsyncImage(
                        imgUrl = groupInfo.groupPhotoUrl,
                        painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_load_img),
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = DuetTheme.colors.backgroundColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )

                    Icon(
                        painter = painterResource(inc.generics.presentation.R.drawable.ic_chain),
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        tint = DuetTheme.colors.thirdColor
                    )

                    if (groupInfo.namePartner != null) {
                        DuetAsyncImage(
                            imgUrl = groupInfo.partnerPhotoUrl,
                            painterIconIfNotImg = painterResource(inc.generics.presentation.R.drawable.ic_profile),
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    color = DuetTheme.colors.backgroundColor,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )

                        Column(
                            modifier = Modifier.padding(vertical = 2.dp).fillMaxHeight()
                                .padding(start = 8.dp)
                        ) {
                            Text(
                                text = groupInfo.namePartner,
                                style = defaultTextStyleDuet().copy(
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start
                                )
                            )
                            Icon(
                                painter = painterResource(
                                    if (groupInfo.isPartnerInGroup)
                                        inc.generics.presentation.R.drawable.ic_like
                                    else inc.generics.presentation.R.drawable.ic_heart_broke
                                ),
                                contentDescription = "",
                                modifier = Modifier.padding(top = 6.dp).size(18.dp),
                                tint = DuetTheme.colors.secondColor
                            )
                        }
                    } else {
                        Text(
                            text = DuetTheme.localization[StringsKeys.NO_PARTNER],
                            style = defaultTextStyleDuet().copy(
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun Settings(routing: ProfileRouting) {
    var isDialogShow by remember {
        mutableStateOf(false)
    }
    var isLanguagesShow by remember {
        mutableStateOf(false)
    }

    if (isDialogShow) {
        DialogGetOut(routing = routing) { newStatus ->
            isDialogShow = newStatus
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = DuetTheme.localization[StringsKeys.SETTINGS],
            style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start, fontSize = 20.sp),
            fontWeight = FontWeight.Bold
        )

        RouteIconButtonAnimated(
            RouteButtonModel(
                title = DuetTheme.localization.nameLanguage,
                additionalText = DuetTheme.localization[StringsKeys.LANGUAGE],
                icon = DuetTheme.localization.iconId
            ),
            onClick = {
                isLanguagesShow = !isLanguagesShow
            }
        )

        AnimatedVisibility(
            visible = isLanguagesShow,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            //todo: показывать список доступных языков
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(DuetTheme.colors.mainColor.copy(alpha = 0.4f))
            ) { }
        }

        RouteIconButton(
            RouteButtonModel(
                title = DuetTheme.colors.themeName,
                additionalText = DuetTheme.localization[StringsKeys.COLOR_THEME],
                icon = null
            ),
            onClick = {
                //todo: открывать выбор темы
            }
        )

        DefaultFilledTonalButtonDuet(
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp),
            text = DuetTheme.localization[StringsKeys.GET_OUT],
            color = DuetTheme.colors.errorColor,
            onClick = {
                isDialogShow = true
            }
        )
    }
}

@Composable
fun DialogGetOut(
    viewModel: ProfileViewModel = koinViewModel(),
    routing: ProfileRouting,
    onCloseDialog: (newDialogStatus: Boolean) -> Unit
) {
    DuetAlertDialogRequest(
        messageText = DuetTheme.localization[StringsKeys.REQUEST_TO_LOGOUT],
        onClose = {},
        onAccept = {
            onCloseDialog(false)
            viewModel.logout()
            routing.toAuth()
        },
        onDismiss = {
            onCloseDialog(false)
        }
    )
}
package inc.generics.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.components.DuetAsyncImage
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import inc.generics.profile.view_models.ProfileViewModel
import inc.generics.profile.view_models.TypeAccount
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ScreenContent(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val screenState by viewModel.screenState.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInformationAboutProfile()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
            .background(DuetTheme.colors.backgroundColor).padding(horizontal = 16.dp)
    ) {
        if (screenState != null) {
            item { UserInfo() }
            item { AccountList() }
            item { GroupInfo() }
        } else {
            item { Loading() }
        }
        item { Settings() }
    }

}

@Composable
internal fun UserInfo(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val screenState by viewModel.screenState.observeAsState()
    screenState?.let { state ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
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
                text = state.groupInfo?.nameGroup ?: "У вас нет активной группы",
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
                            modifier = Modifier.padding(vertical = 2.dp).fillMaxHeight().padding(start = 8.dp)
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
                            text = "Нет партнера",
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
internal fun Settings() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
    ) {
        Text(
            text = "Настройки",
            style = defaultTextStyleDuet().copy(textAlign = TextAlign.Start, fontSize = 20.sp),
            fontWeight = FontWeight.Bold
        )
    }
}
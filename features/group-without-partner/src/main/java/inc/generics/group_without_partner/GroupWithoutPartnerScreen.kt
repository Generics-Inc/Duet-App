package inc.generics.group_without_partner

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import inc.generics.group_without_partner.routing.GroupWithoutPartnerRouting
import inc.generics.presentation.R
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupWithoutPartnerScreen(
    routing: GroupWithoutPartnerRouting,
    viewModel: GroupWithoutPartnerViewModel = koinViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.getGroup()
    }

    val isLeaved by viewModel.isLeaved.observeAsState(false)
    if (isLeaved) {
        LaunchedEffect(Unit) {
            routing.routToMain()
        }
        return
    }

    val loadStatus by viewModel.loadStatus.observeAsState(initial = LoadStatus.NONE)
    Scaffold(
        topBar = {
            DefaultTopAppBarDuet()
        }
    ) {
        when (loadStatus) {
            LoadStatus.NONE -> {
                //loading
            }
            LoadStatus.ERROR -> {
                //show dialog error
            }
            else -> {
                ContentScreen(routing = routing, paddingValues = it)
            }
        }
    }
}

@Composable
fun ContentScreen(
    routing: GroupWithoutPartnerRouting,
    paddingValues: PaddingValues,
    viewModel: GroupWithoutPartnerViewModel = koinViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DuetTheme.colors.backgroundColor)
            .padding(paddingValues)
    ) {
        Column {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ImageGroup()
                NameGroup()
                ButtonEdit()
                ButtonToJoinRequests()
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                InviteCodeFragment()
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                DefaultFilledTonalButtonDuet(
                    onClick = { viewModel.leaveGroup() },
                    text = "Выйти",
                    color = DuetTheme.colors.errorColor,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Composable
fun InviteCodeFragment(viewModel: GroupWithoutPartnerViewModel = koinViewModel()) {
    val groupWithoutPartner by viewModel.groupWithoutPartner.observeAsState()
    val clipboardManager =
        LocalContext.current.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    var isShowToast by remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxWidth()
    ) {
        AnimatedVisibility(
            visible = isShowToast,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            Text(
                text = "Код скопирован",
                Modifier
                    .background(
                        color = DuetTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                color = DuetTheme.colors.backgroundColor
            )
        }

        groupWithoutPartner?.let {
            OutlinedButtonDuet(
                onClick = {
                    val clip = ClipData.newPlainText("Invite Code", it.inviteCode)
                    clipboardManager.setPrimaryClip(clip)
                    CoroutineScope(Dispatchers.IO).launch {
                        isShowToast = true
                        delay(1500)
                        isShowToast = false
                    }
                },
                hasElevation = false,
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.offset(
                    y = animateDpAsState(
                        if (isShowToast) 32.dp else 0.dp,
                        label = "anim show toast"
                    ).value
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.inviteCode,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                        color = DuetTheme.colors.secondColor,
                        fontSize = 36.sp,
                        fontFamily = FontFamily.Monospace
                    )

                    Text(
                        text = "нажмите чтобы скопировать код",
                        style = defaultTextStyleDuet().copy(
                            color = DuetTheme.colors.secondColor,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ImageGroup(viewModel: GroupWithoutPartnerViewModel = koinViewModel()) {
    val groupWithoutPartner by viewModel.groupWithoutPartner.observeAsState(null)

    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(top = 14.dp)
            .background(
                color = DuetTheme.colors.mainColor.copy(0.4f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        groupWithoutPartner?.let {
            if (it.photoUrl == null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_load_img),
                    tint = DuetTheme.colors.mainColor,
                    contentDescription = "icon load image",
                )
            } else {
                AsyncImage(
                    model = LocalContext.current.getString(R.string.base_url) + it.photoUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }

}

@Composable
fun NameGroup(viewModel: GroupWithoutPartnerViewModel = koinViewModel()) {
    val groupWithoutPartner by viewModel.groupWithoutPartner.observeAsState()
    Log.d("groupWithoutPartner",groupWithoutPartner.toString())
    groupWithoutPartner?.let {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = it.name,
            style = defaultTextStyleDuet().copy(fontSize = 20.sp)
        )
    }
}

@Composable
fun ButtonEdit() {
    DefaultFilledTonalButtonDuet(
        onClick = {},
        text = "Редактировать",
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun ButtonToJoinRequests() {
    DefaultOutlinedButtonDuet(
        onClick = {},
        text = "Заявки",
        modifier = Modifier.padding(top = 8.dp)
    )
}
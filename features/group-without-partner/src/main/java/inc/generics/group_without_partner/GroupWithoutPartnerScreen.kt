package inc.generics.group_without_partner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.group_without_partner.routing.GroupWithoutPartnerRouting
import inc.generics.presentation.R
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.DefaultTopAppBarDuet
import inc.generics.presentation.components.OutlinedButtonDuet

import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme

@Composable
fun GroupWithoutPartnerScreen(routing: GroupWithoutPartnerRouting) {
    Scaffold(
        topBar = {
            DefaultTopAppBarDuet()
        }
    ) {
        ContentScreen(routing = routing, paddingValues = it)
    }
}

@Composable
fun ContentScreen(routing: GroupWithoutPartnerRouting, paddingValues: PaddingValues) {

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
                    onClick = {},
                    text = "Выйти",
                    color = DuetTheme.colors.errorColor,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Composable
fun InviteCodeFragment() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButtonDuet(
            onClick = {},
            hasElevation = false,
            shape = RoundedCornerShape(30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "G778HPDXX9",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                    color = DuetTheme.colors.secondColor,
                    fontSize = 36.sp
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

        DefaultOutlinedButtonDuet(
            onClick = {},
            text = "Получить новый",
            modifier = Modifier
                .width(240.dp)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun ImageGroup() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(top = 14.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_load_img),
            tint = DuetTheme.colors.mainColor,
            contentDescription = "icon load image",
        )
    }
}

@Composable
fun NameGroup() {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = "Название группы",
        style = defaultTextStyleDuet().copy(fontSize = 20.sp)
    )
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
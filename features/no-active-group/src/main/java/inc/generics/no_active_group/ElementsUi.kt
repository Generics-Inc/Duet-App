package inc.generics.no_active_group

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import inc.generics.no_active_group.routing.NoActiveGroupRouting
import inc.generics.presentation.R
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun UserWithoutGroup(
    viewModel: NoActiveGroupViewModel = koinViewModel(),
    routing: NoActiveGroupRouting = koinInject()
) {
    HeadTestAndIcon("У вас нет\nактивной группы")

    DefaultOutlinedButtonDuet(
        onClick = { routing.routToCreateGroup() },
        text = "Создать",
        modifier = Modifier
            .padding(top = 65.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )

    DefaultOutlinedButtonDuet(
        onClick = { routing.routToJoinGroup() },
        text = "Присоединиться",
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )

    DefaultFilledTonalButtonDuet(
        onClick = { routing.routToListOfDeletedGroups() },
        text = "Корзина" ,
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )
}

@Composable
fun NoPartnerInGroup(
    partnerLeavedGroup: Boolean,
    viewModel: NoActiveGroupViewModel = koinViewModel(),
    routing: NoActiveGroupRouting = koinInject()
) {
    if (partnerLeavedGroup) {
        HeadTestAndIcon("Ваш партнер\nвышел из группы")
        DefaultOutlinedButtonDuet(
            onClick = { routing.routToCreateInviteCode() },
            text = "Новый код",
            modifier = Modifier
                .padding(top = 65.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )
    } else {
        HeadTestAndIcon("Ваш партнер\nудалил группу")
    }

    DefaultOutlinedButtonDuet(
        onClick = { viewModel.saveArchiveThisGroup() },
        text = "Скачать архив",
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )

    DefaultFilledTonalButtonDuet(
        onClick = {  },
        text = "Удалить",
        color = DuetTheme.colors.errorColor,
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )
}

@Composable
fun HeadTestAndIcon(text: String) {
    Icon(
        painter = painterResource(id = R.drawable.ic_heart_broke),
        contentDescription = "broke heart",
        tint = DuetTheme.colors.mainColor,
        modifier = Modifier.padding(top = 100.dp)
    )

    Text(
        text = text,
        style = defaultTextStyleDuet(),
        modifier = Modifier
            .padding(top = 35.dp)
    )
}
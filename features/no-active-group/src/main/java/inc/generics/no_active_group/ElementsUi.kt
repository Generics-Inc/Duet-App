package inc.generics.no_active_group

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.generics.no_active_group.models.NoActiveGroupUiData
import inc.generics.no_active_group.routing.NoActiveGroupRouting
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DefaultOutlinedButtonDuet
import inc.generics.presentation.components.HeadTestAndIcon
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.CutterType
import org.koin.compose.koinInject

@Composable
fun UserWithoutGroup(
    routing: NoActiveGroupRouting,
    noActiveGroupUiData: NoActiveGroupUiData
) {
    HeadTestAndIcon(text = DuetTheme.localization.getString("noActiveGroup"))

    DefaultOutlinedButtonDuet(
        onClick = { routing.routToCreateGroup() },
        text = DuetTheme.localization.getString("create"),
        cutterType = CutterType.ONLY_ONE_CLICK,
        modifier = Modifier
            .padding(top = 65.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )

    DefaultOutlinedButtonDuet(
        onClick = { routing.routToJoinGroup() },
        text = DuetTheme.localization.getString("intoToGroup"),
        cutterType = CutterType.ONLY_ONE_CLICK,
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )
    
    if (noActiveGroupUiData.userHaveArchive) {
        DefaultFilledTonalButtonDuet(
            onClick = { routing.routToListOfDeletedGroups() },
            text = DuetTheme.localization.getString("basket") ,
            cutterType = CutterType.ONLY_ONE_CLICK,
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )
    }
}

@Composable
fun NoPartnerInGroup(
    partnerLeavedGroup: Boolean,
    routing: NoActiveGroupRouting = koinInject()
) {
    if (partnerLeavedGroup) {
        HeadTestAndIcon("Ваш партнер\nвышел из группы")
        DefaultOutlinedButtonDuet(
            onClick = {  },
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
        onClick = {  },
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
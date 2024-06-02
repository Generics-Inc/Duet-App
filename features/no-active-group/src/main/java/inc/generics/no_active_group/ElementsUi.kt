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
import inc.generics.presentation.theme.localization.StringsKeys
import inc.generics.presentation.utils.CutterType
import org.koin.compose.koinInject

@Composable
fun UserWithoutGroup(
    routing: NoActiveGroupRouting,
    noActiveGroupUiData: NoActiveGroupUiData
) {
    HeadTestAndIcon(text = DuetTheme.localization[StringsKeys.NO_ACTIVE_GROUP])

    DefaultOutlinedButtonDuet(
        onClick = { routing.routToCreateGroup() },
        text = DuetTheme.localization[StringsKeys.CREATE],
        cutterType = CutterType.ONLY_ONE_CLICK,
        modifier = Modifier
            .padding(top = 65.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )

    DefaultOutlinedButtonDuet(
        onClick = { routing.routToJoinGroup() },
        text = DuetTheme.localization[StringsKeys.INTO_TO_GROUP],
        cutterType = CutterType.ONLY_ONE_CLICK,
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    )
    
    if (noActiveGroupUiData.userHaveArchive) {
        DefaultFilledTonalButtonDuet(
            onClick = { routing.routToListOfDeletedGroups() },
            text = DuetTheme.localization[StringsKeys.BASKET] ,
            cutterType = CutterType.ONLY_ONE_CLICK,
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
        )
    }
}
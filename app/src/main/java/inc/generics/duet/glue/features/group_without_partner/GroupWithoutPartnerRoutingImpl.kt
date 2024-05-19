package inc.generics.duet.glue.features.group_without_partner

import androidx.navigation.NavHostController
import inc.generics.duet.StatusGroup
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.navigateWithDataInclusive
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.group_without_partner.routing.GroupWithoutPartnerRouting

class GroupWithoutPartnerRoutingImpl(
    private val navigator: NavHostController
) : GroupWithoutPartnerRouting {
    override fun routToEditingGroup() {
        //navigator.navigate()
    }

    override fun routToAllRequestsToJoin() {
        //navigator.navigate()
    }

    override fun routToMain() {
        navigator.navigateWithDataInclusive(
            ExternalScreens.NoActiveGroup.route,
            true.toString()
        )
    }
}
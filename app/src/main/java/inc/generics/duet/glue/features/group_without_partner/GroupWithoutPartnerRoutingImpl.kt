package inc.generics.duet.glue.features.group_without_partner

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateReplaceWithData
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.group_without_partner.routing.GroupWithoutPartnerRouting

class GroupWithoutPartnerRoutingImpl(
    private val navigator: NavHostController
) : GroupWithoutPartnerRouting {
    override fun routToEditingGroup() {
        //navigator.navigate()
    }

    override fun routToAllRequestsToJoin() {
        navigator.navigate(ExternalScreens.Requests.route)
    }

    override fun routToNoActiveGroup() {
        navigator.navigateReplaceWithData(
            ExternalScreens.NoActiveGroup.route,
            ExternalScreens.GroupWithoutPartner.route,
            true.toString()
        )
    }
}
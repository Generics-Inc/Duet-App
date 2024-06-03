package inc.generics.duet.glue.features.group_left_by_partner

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.navigateReplace
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.group_left_by_partner.routing.GroupLeftByPartnerRouting

class GroupLeftByPartnerRoutingImpl(
    private val navHostController: NavHostController
): GroupLeftByPartnerRouting {
    override fun toNewInviteCode() {
        navHostController.navigate(ExternalScreens.JoinToGroup.route)
    }

    override fun toSaveArchive() {
        TODO("Not yet implemented")
    }

    override fun toMain() {
        navHostController.navigateInclusive(ExternalScreens.Main.route)
    }
}
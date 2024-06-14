package inc.generics.duet.glue.features.group

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.group.routing.GroupRouting

class GroupRoutingImpl(
    private val navHostController: NavHostController
) : GroupRouting {
    override fun routToMain() {
        navHostController.navigateInclusive(ExternalScreens.Main.route)
    }

    override fun back() {
        navHostController.popBackStack()
    }
}
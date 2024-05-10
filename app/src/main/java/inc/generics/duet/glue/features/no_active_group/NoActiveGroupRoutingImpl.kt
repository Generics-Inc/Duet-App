package inc.generics.duet.glue.features.no_active_group

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.no_active_group.routing.NoActiveGroupRouting

class NoActiveGroupRoutingImpl(private val navController: NavHostController) : NoActiveGroupRouting {
    override fun routToCreateGroup() {
        navController.navigate(ExternalScreens.CreateNewGroup.route)
    }

    override fun routToListOfDeletedGroups() {}

    override fun routToJoinGroup() {}
}
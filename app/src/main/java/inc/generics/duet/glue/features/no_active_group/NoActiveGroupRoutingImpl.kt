package inc.generics.duet.glue.features.no_active_group

import androidx.navigation.NavHostController
import inc.generics.no_active_group.routing.NoActiveGroupRouting

class NoActiveGroupRoutingImpl(navController: NavHostController) : NoActiveGroupRouting {
    override fun routToCreateGroup() {}

    override fun routToListOfDeletedGroups() {}

    override fun routToJoinGroup() {}

    override fun routToCreateInviteCode() {}
}
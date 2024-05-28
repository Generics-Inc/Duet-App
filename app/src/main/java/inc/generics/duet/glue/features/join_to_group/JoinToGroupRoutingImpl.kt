package inc.generics.duet.glue.features.join_to_group

import androidx.navigation.NavHostController
import inc.generics.join_to_group.routing.JoinToGroupRouting

class JoinToGroupRoutingImpl(private val navHostController: NavHostController) : JoinToGroupRouting {
    override fun goBack() {
        navHostController.popBackStack()
    }
}
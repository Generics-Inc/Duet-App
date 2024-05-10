package inc.generics.duet.glue.features.create_new_group

import androidx.navigation.NavHostController
import inc.generics.create_new_group.routing.CreateNewGroupScreenRouting
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.screens.ExternalScreens

class CreateNewGroupScreenRoutingImpl(
    private val navController: NavHostController
) : CreateNewGroupScreenRouting {
    override fun routToMain() {
        navController.navigateInclusive(ExternalScreens.Main.route)
    }

    override fun back() {
        navController.popBackStack()
    }
}
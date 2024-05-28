package inc.generics.duet.glue.features.requests

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.requests.routing.RequestsRouting

class RequestRoutingImpl(private val navHostController: NavHostController) : RequestsRouting {
    override fun goBack() {
        navHostController.popBackStack()
    }

    override fun toMain() {
        navHostController.navigateInclusive(ExternalScreens.Main.route)
    }
}
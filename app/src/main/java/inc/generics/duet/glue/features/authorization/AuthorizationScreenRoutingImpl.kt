package inc.generics.duet.glue.features.authorization

import androidx.navigation.NavHostController
import inc.generics.authorization.routing.AuthorizationScreenRouting
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.screens.ExternalScreens

class AuthorizationScreenRoutingImpl(
    private val navController: NavHostController
) : AuthorizationScreenRouting {
    override fun routToMain() {
        navController.navigateInclusive(ExternalScreens.Main.route)
    }
}
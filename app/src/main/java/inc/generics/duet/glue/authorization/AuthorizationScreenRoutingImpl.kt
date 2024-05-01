package inc.generics.duet.glue.authorization

import androidx.navigation.NavHostController
import inc.generics.authorization.routing.AuthorizationScreenRouting
import inc.generics.duet.navigation.screens.ExternalScreens

class AuthorizationScreenRoutingImpl(
    private val navController: NavHostController
) : AuthorizationScreenRouting {
    override fun routToMain() {
        navController.navigate(ExternalScreens.Main.route)
    }
}
package inc.generics.duet.glue.features.profile.di

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.profile.routing.ProfileRouting

class ProfileRoutingImpl(
    private val navHostController: NavHostController
) : ProfileRouting {
    override fun toBack() {
        navHostController.popBackStack()
    }

    override fun toAuth() {
        navHostController.navigateInclusive(ExternalScreens.Authorization.route)
    }

    override fun toGroup() {
        navHostController.navigate(ExternalScreens.Group.route)
    }
}
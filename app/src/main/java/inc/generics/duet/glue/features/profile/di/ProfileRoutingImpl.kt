package inc.generics.duet.glue.features.profile.di

import androidx.navigation.NavHostController
import inc.generics.profile.routing.ProfileRouting

class ProfileRoutingImpl(
    private val navHostController: NavHostController
) : ProfileRouting {
    override fun toBack() {
        navHostController.popBackStack()
    }
}
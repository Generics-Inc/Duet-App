package inc.generics.duet.glue.features.archive

import androidx.navigation.NavHostController
import inc.generics.archive.routing.ArchiveRouting
import inc.generics.duet.navigation.navigateInclusive
import inc.generics.duet.navigation.screens.ExternalScreens

class ArchiveRoutingImpl(
    private val navHostController: NavHostController
): ArchiveRouting {
    override fun back() {
        navHostController.popBackStack()
    }

    override fun routToMain() {
        navHostController.navigateInclusive(ExternalScreens.Main.route)
    }
}
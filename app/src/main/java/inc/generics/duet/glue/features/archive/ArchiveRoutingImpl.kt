package inc.generics.duet.glue.features.archive

import androidx.navigation.NavHostController
import inc.generics.archive.routing.ArchiveRouting

class ArchiveRoutingImpl(
    private val navHostController: NavHostController
): ArchiveRouting {
    override fun back() {
        navHostController.popBackStack()
    }
}
package inc.generics.duet.glue.features.movie

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.movie.routing.MovieRouting

class MovieRoutingImpl(
    private val externalNavHostController: NavHostController
) : MovieRouting {
    override fun onProfile() {
        externalNavHostController.navigate(ExternalScreens.Profile.route)
    }

    override fun onMovie() {}

    override fun onCreateNewMovie() {}
}
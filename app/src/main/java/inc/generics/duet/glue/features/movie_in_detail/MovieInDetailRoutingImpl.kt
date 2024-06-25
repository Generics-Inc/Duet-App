package inc.generics.duet.glue.features.movie_in_detail

import androidx.navigation.NavHostController
import inc.generics.movie_in_detail.routing.MovieInDetailRouting

class MovieInDetailRoutingImpl(
    private val navHostController: NavHostController
) : MovieInDetailRouting {
    override fun back() {
        navHostController.popBackStack()
    }
}
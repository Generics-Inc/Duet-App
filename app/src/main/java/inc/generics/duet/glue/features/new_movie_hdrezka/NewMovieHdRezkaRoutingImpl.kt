package inc.generics.duet.glue.features.new_movie_hdrezka

import androidx.navigation.NavHostController
import inc.generics.new_movie_hdrezka.routing.NewMovieHdRezkaRouting

class NewMovieHdRezkaRoutingImpl(
    private val navHostController: NavHostController
): NewMovieHdRezkaRouting {
    override fun back() {
        navHostController.popBackStack()
    }
}
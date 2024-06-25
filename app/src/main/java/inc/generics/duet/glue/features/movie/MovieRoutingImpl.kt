package inc.generics.duet.glue.features.movie

import androidx.navigation.NavHostController
import inc.generics.duet.navigation.navigateReplaceWithData
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.duet.navigation.screens.NavBarScreens
import inc.generics.movie.routing.MovieRouting

class MovieRoutingImpl(
    private val externalNavHostController: NavHostController
) : MovieRouting {
    override fun onProfile() {
        externalNavHostController.navigate(ExternalScreens.Profile.route)
    }

    override fun onMovie(idMovie: Long) {
        externalNavHostController.navigateReplaceWithData(
            routeTo = ExternalScreens.MovieInDetail.route,
            routeFrom = NavBarScreens.MoviesScreen.route,
            dataAsString = idMovie.toString()
        )
    }

    override fun onCreateNewMovie() {}
    override fun onCreateNewMovieHdRezka() {
        externalNavHostController.navigate(ExternalScreens.NewMovieHdRezka.route)
    }
}
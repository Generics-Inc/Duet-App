package inc.generics.duet.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.generics.duet.glue.features.movie.MovieRoutingImpl
import inc.generics.duet.navigation.screens.NavBarScreens
import inc.generics.movie.MovieScreen

@Composable
fun SetupSubNavGraph(
    externalNavHostController: NavHostController,
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = NavBarScreens.MoviesScreen.route,
        modifier = Modifier.padding(paddingValues),
        enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, tween(300)
            )
        }
    ) {
        composable(route = NavBarScreens.MoviesScreen.route) {
            MovieScreen(MovieRoutingImpl(externalNavHostController))
        }
        composable(route = NavBarScreens.EventsScreen.route) {}
        composable(route = NavBarScreens.KitchenScreen.route) {}
    }
}

val listOfNavBarScreens = listOf(
    NavBarScreens.MoviesScreen,
    NavBarScreens.EventsScreen,
    NavBarScreens.KitchenScreen
)
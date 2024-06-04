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
import inc.generics.duet.navigation.screens.NavBarScreens
import inc.generics.profile.ProfileScreen

@Composable
fun SetupSubNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = NavBarScreens.Profile.route,
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
        composable(route = NavBarScreens.Profile.route) {
            ProfileScreen()
        }
    }
}

val listOfNavBarScreens = listOf(
    NavBarScreens.Profile
)
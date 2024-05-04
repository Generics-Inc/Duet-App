package inc.generics.duet.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.generics.authorization.AuthorizationScreen
import inc.generics.duet.TestScreen
import inc.generics.duet.glue.features.authorization.AuthorizationScreenRoutingImpl
import inc.generics.duet.glue.features.no_active_group.NoActiveGroupRoutingImpl
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.no_active_group.NoActiveGroupScreen
import inc.generics.no_active_group.models.GroupInf

@Composable
fun SetupMainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ExternalScreens.Main.route,
        enterTransition = {
            fadeIn(animationSpec = tween(500)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
            )
        }
    ) {
        composable(route = ExternalScreens.Main.route) {
            TestScreen()
        }
        composable(route = ExternalScreens.NoActiveGroup.route) {
            val groupInf: GroupInf? = navHostController.getData("groupInf") as GroupInf?
            groupInf?.let {
                NoActiveGroupScreen(
                groupInf = it,
                routing = NoActiveGroupRoutingImpl(navHostController)
                )
            }
        }
        composable(route = ExternalScreens.Authorization.route) {
            AuthorizationScreen(router = AuthorizationScreenRoutingImpl(navHostController))
        }
    }
}
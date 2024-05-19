package inc.generics.duet.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import inc.generics.authorization.AuthorizationScreen
import inc.generics.create_new_group.CreateNewGroupScreen
import inc.generics.duet.Main
import inc.generics.duet.glue.features.authorization.AuthorizationScreenRoutingImpl
import inc.generics.duet.glue.features.create_new_group.CreateNewGroupScreenRoutingImpl
import inc.generics.duet.glue.features.group_without_partner.GroupWithoutPartnerRoutingImpl
import inc.generics.duet.glue.features.no_active_group.NoActiveGroupRoutingImpl
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.group_without_partner.GroupWithoutPartnerScreen
import inc.generics.no_active_group.NoActiveGroupScreen
import inc.generics.no_active_group.models.NoActiveGroupUiData

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
            Main(mainNavController = navHostController)
        }
        composable(
            route = "${ExternalScreens.NoActiveGroup.route}/{data}",
            arguments = listOf(navArgument("data") { type = NavType.BoolType })
        ) {
            val data = navHostController.currentBackStackEntry?.arguments?.getBoolean("data")
            data?.let {
                NoActiveGroupScreen(
                    routing = NoActiveGroupRoutingImpl(navHostController),
                    noActiveGroupUiData = NoActiveGroupUiData(data)
                )
            }
        }
        composable(route = ExternalScreens.Authorization.route) {
            AuthorizationScreen(router = AuthorizationScreenRoutingImpl(navHostController))
        }
        composable(route = ExternalScreens.CreateNewGroup.route) {
            CreateNewGroupScreen(routing = CreateNewGroupScreenRoutingImpl(navHostController))
        }
        composable(route = ExternalScreens.GroupWithoutPartner.route) {
            GroupWithoutPartnerScreen(routing = GroupWithoutPartnerRoutingImpl(navHostController))
        }
    }
}
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
import inc.generics.archive.ArchiveScreen
import inc.generics.authorization.AuthorizationScreen
import inc.generics.create_new_group.CreateNewGroupScreen
import inc.generics.duet.glue.features.archive.ArchiveRoutingImpl
import inc.generics.duet.ui.Main
import inc.generics.duet.ui.SubNavBar
import inc.generics.duet.glue.features.authorization.AuthorizationScreenRoutingImpl
import inc.generics.duet.glue.features.create_new_group.CreateNewGroupScreenRoutingImpl
import inc.generics.duet.glue.features.group.GroupRoutingImpl
import inc.generics.duet.glue.features.group_left_by_partner.GroupLeftByPartnerRoutingImpl
import inc.generics.duet.glue.features.group_without_partner.GroupWithoutPartnerRoutingImpl
import inc.generics.duet.glue.features.join_to_group.JoinToGroupRoutingImpl
import inc.generics.duet.glue.features.new_movie_hdrezka.NewMovieHdRezkaRoutingImpl
import inc.generics.duet.glue.features.no_active_group.NoActiveGroupRoutingImpl
import inc.generics.duet.glue.features.profile.di.ProfileRoutingImpl
import inc.generics.duet.glue.features.requests.RequestRoutingImpl
import inc.generics.duet.navigation.screens.ExternalScreens.*
import inc.generics.group.GroupScreen
import inc.generics.group_left_by_partner.GroupLeftByPartnerScreen
import inc.generics.group_left_by_partner.models.StatusGroupLeftByPartner
import inc.generics.group_without_partner.GroupWithoutPartnerScreen
import inc.generics.join_to_group.JoinToGroupScreen
import inc.generics.new_movie_hdrezka.NewMovieHdRezkaScreen
import inc.generics.no_active_group.NoActiveGroupScreen
import inc.generics.no_active_group.models.NoActiveGroupUiData
import inc.generics.profile.ProfileScreen
import inc.generics.requests.RequestsScreen

@Composable
fun SetupMainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Main.route,
        enterTransition = {
            fadeIn(animationSpec = tween(500)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
            )
        },
    ) {
        composable(
            route = Main.route,
            enterTransition = {
                fadeIn(animationSpec = tween(200))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(200))
            }
        ) {
            Main(mainNavController = navHostController)
        }
        composable(
            route = "${NoActiveGroup.route}/{data}",
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
        composable(route = Authorization.route) {
            AuthorizationScreen(router = AuthorizationScreenRoutingImpl(navHostController))
        }
        composable(route = CreateNewGroup.route) {
            CreateNewGroupScreen(routing = CreateNewGroupScreenRoutingImpl(navHostController))
        }
        composable(route = GroupWithoutPartner.route) {
            GroupWithoutPartnerScreen(routing = GroupWithoutPartnerRoutingImpl(navHostController))
        }
        composable(route = Requests.route) {
            RequestsScreen(routing = RequestRoutingImpl(navHostController))
        }
        composable(route = JoinToGroup.route) {
            JoinToGroupScreen(routing = JoinToGroupRoutingImpl(navHostController))
        }
        composable(route = GroupLeftByPartner.route) {
            openScreenWithData<StatusGroupLeftByPartner>(
                navHostController,
                GroupLeftByPartner.dataKey
            ) { dataScreen ->
                GroupLeftByPartnerScreen(
                    routing = GroupLeftByPartnerRoutingImpl(navHostController),
                    status = dataScreen
                )
            }
        }
        composable(route = Profile.route) {
            ProfileScreen(ProfileRoutingImpl(navHostController))
        }
        composable(route = Archive.route) {
            ArchiveScreen(ArchiveRoutingImpl(navHostController))
        }
        composable(route = Group.route) {
            GroupScreen(GroupRoutingImpl(navHostController))
        }
        composable(route = NewMovieHdRezka.route) {
            NewMovieHdRezkaScreen(NewMovieHdRezkaRoutingImpl(navHostController))
        }

        composable(route = SubNavigation.route) {
            SubNavBar(externalNavHostController =  navHostController)
        }
    }
}

@Composable
inline fun <reified T> openScreenWithData(
    navHostController: NavHostController,
    keyData: String,
    openScreen: @Composable (dataScreen: T) -> Unit) {
    val screenData = navHostController.getData(keyData)
    if (screenData is T) {
        openScreen(screenData)
    }
}
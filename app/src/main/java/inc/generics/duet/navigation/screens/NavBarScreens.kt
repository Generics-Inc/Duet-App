package inc.generics.duet.navigation.screens

import inc.generics.duet.R

sealed class NavBarScreens(val route: String, val id: Int, val idIcon: Int) {
    data object MoviesScreen : NavBarScreens(
        route = "MoviesScreen",
        id = 0,
        inc.generics.presentation.R.drawable.ic_profile
    )
}
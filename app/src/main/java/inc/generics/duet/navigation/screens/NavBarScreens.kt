package inc.generics.duet.navigation.screens

sealed class NavBarScreens(val route: String, val id: Int, val idIcon: Int) {
    data object MoviesScreen : NavBarScreens(
        route = "MoviesScreen",
        id = 0,
        inc.generics.presentation.R.drawable.ic_profile
    )

    data object EventsScreen : NavBarScreens(
        route = "EventsScreen",
        id = 1,
        inc.generics.presentation.R.drawable.ic_events_block
    )

    data object KitchenScreen : NavBarScreens(
        route = "KitchenScreen",
        id = 2,
        inc.generics.presentation.R.drawable.ic_kitchen_block
    )
}
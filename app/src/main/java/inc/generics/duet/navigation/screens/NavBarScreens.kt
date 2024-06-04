package inc.generics.duet.navigation.screens

sealed class NavBarScreens(val route: String) {
    data object Profile : NavBarScreens(route = "ProfileScreen")
}
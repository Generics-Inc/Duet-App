package inc.generics.duet.navigation.screens

sealed class ExternalScreens(val route: String) {
    data object Main : ExternalScreens(route = "externalMainScreen")
    data object NoActiveGroup : ExternalScreens(route = "noActiveGroupScreen")
    data object Authorization : ExternalScreens(route = "authorizationScreen")
}

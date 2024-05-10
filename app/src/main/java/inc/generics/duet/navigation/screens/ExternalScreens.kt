package inc.generics.duet.navigation.screens

sealed class ExternalScreens(val route: String, val dataKey: String) {
    data object Main : ExternalScreens(route = "externalMainScreen", "")
    data object NoActiveGroup : ExternalScreens(route = "noActiveGroupScreen", "dataNoActiveGroup")
    data object Authorization : ExternalScreens(route = "authorizationScreen", "")
    data object CreateNewGroup: ExternalScreens(route = "createNewGroup", "")
}

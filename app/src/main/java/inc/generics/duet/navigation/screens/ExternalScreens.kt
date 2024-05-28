package inc.generics.duet.navigation.screens

sealed class ExternalScreens(val route: String, val dataKey: String) {
    data object Main : ExternalScreens(route = "externalMainScreen", "")
    data object NoActiveGroup : ExternalScreens(route = "noActiveGroupScreen", "")
    data object Authorization : ExternalScreens(route = "authorizationScreen", "")
    data object CreateNewGroup: ExternalScreens(route = "createNewGroup", "")
    data object GroupWithoutPartner: ExternalScreens(route = "groupWithoutPartner", "")
    data object Requests: ExternalScreens(route = "requestsScreen", "")
    data object JoinToGroup: ExternalScreens(route = "joinToGroup", "")
}

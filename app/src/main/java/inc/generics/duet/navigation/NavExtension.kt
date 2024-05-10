package inc.generics.duet.navigation

import android.os.Parcelable
import androidx.navigation.NavHostController

fun NavHostController.navigateWithData(route: String, pair: Pair<String, Parcelable>) {
    this.currentBackStackEntry?.savedStateHandle?.set(pair.first, pair.second)
    this.navigate(route = route)
}

fun NavHostController.getData(key: String): Parcelable? =
    this.previousBackStackEntry?.savedStateHandle?.get<Parcelable>(key)

fun NavHostController.navigateInclusive(route: String) {
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}
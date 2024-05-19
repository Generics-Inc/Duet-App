package inc.generics.duet.navigation

import android.os.Parcelable
import androidx.navigation.NavHostController

fun NavHostController.navigateWithData(route: String, pair: Pair<String, Parcelable>) {
    this.currentBackStackEntry?.savedStateHandle?.set(pair.first, pair.second)
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateWithDataInclusive(route: String, dataAsString: String) {
    this.popBackStack()
    this.navigate("$route/$dataAsString") {
        popUpTo(route) {
            inclusive = true
        }
    }
}

fun NavHostController.getData(key: String): Parcelable? {
    return this.previousBackStackEntry?.savedStateHandle?.get<Parcelable>(key)
}


fun NavHostController.navigateInclusive(route: String) {
    this.popBackStack()
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}

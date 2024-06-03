package inc.generics.duet.navigation

import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController

fun NavHostController.navigateWithData(route: String, pair: Pair<String, Parcelable>) {
    this.currentBackStackEntry?.savedStateHandle?.set(pair.first, pair.second)
    this.navigate(route)
}


fun NavHostController.navigateReplaceWithData(routeTo: String, routeFrom: String, pair: Pair<String, Parcelable>) {
    this.navigate(routeTo) {
        popUpTo(routeFrom) {
            inclusive = true
            saveState = true
        }
    }
    this.currentBackStackEntry?.savedStateHandle?.set(pair.first, pair.second)
}

fun NavHostController.navigateWithDataInclusive(route: String, dataAsString: String) {
    clearBackStack()
    this.navigate("$route/$dataAsString") {
        popUpTo(route) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateReplaceWithData(routeTo: String, routeFrom: String, dataAsString: String) {
    this.navigate("$routeTo/$dataAsString") {
        popUpTo(routeFrom) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateInclusive(route: String) {
    clearBackStack()
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateReplace(routeTo: String, routeFrom: String) {
    this.navigate(routeTo) {
        popUpTo(routeFrom) {
            inclusive = true
        }
    }
}

fun NavHostController.getData(key: String): Parcelable? {
    return this.currentBackStackEntry?.savedStateHandle?.get<Parcelable>(key)
}

@SuppressLint("RestrictedApi")
fun NavHostController.clearBackStack() {
    repeat(this.currentBackStack.value.size) {
        this.popBackStack()
    }
}
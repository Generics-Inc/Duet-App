package inc.generics.duet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import inc.generics.duet.navigation.SetupMainNavGraph
import inc.generics.duet.navigation.screens.ExternalScreens
import inc.generics.presentation.theme.AppDuetTheme
import inc.generics.presentation.theme.appDuetThemeViewModel
import inc.generics.presentation.theme.localization.Language
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.getScopeId

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.refreshTokens()

        setContent {
            val language by appDuetThemeViewModel().localization.observeAsState(
                Language.En()
            )
            AppDuetTheme(localization = language) {
                val mainNavController = rememberNavController()
                SetupMainNavGraph(navHostController = mainNavController)
            }
        }
    }
}

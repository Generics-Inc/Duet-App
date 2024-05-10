package inc.generics.duet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import inc.generics.duet.navigation.SetupMainNavGraph
import inc.generics.presentation.theme.AppDuetTheme
import inc.generics.presentation.theme.appDuetThemeViewModel
import inc.generics.presentation.theme.localization.Language

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

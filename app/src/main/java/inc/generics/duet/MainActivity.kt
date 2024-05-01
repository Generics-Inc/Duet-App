package inc.generics.duet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import inc.generics.duet.navigation.SetupMainNavGraph
import inc.generics.presentation.theme.AppDuetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppDuetTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val mainNavController = rememberNavController()
    SetupMainNavGraph(navHostController = mainNavController)
}

@Composable
fun TestScreen() {
}
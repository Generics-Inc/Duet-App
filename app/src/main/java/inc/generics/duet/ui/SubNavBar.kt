package inc.generics.duet.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.my.AnimatedNavigationBar
import com.exyte.animatednavbar.utils.noRippleClickable
import inc.generics.duet.navigation.SetupSubNavGraph
import inc.generics.duet.navigation.listOfNavBarScreens
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.utils.CutterType
import inc.generics.presentation.utils.EventsCutter
import inc.generics.presentation.utils.get

@Composable
fun SubNavBar() {
    val navController: NavHostController = rememberNavController()
    val eventsCutter by remember {
        mutableStateOf(
            EventsCutter.get(CutterType.MULTIPLE_EVENTS)
        )
    }
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                modifier = Modifier
                    .height(64.dp)
                    .background(DuetTheme.colors.backgroundColor)
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                selectedIndex = selectedIndex,
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                cornerRadius = shapeCornerRadius(34f),
                barColor = DuetTheme.colors.mainColor,
                ballComposable = {
                    Icon(
                        painterResource(id = inc.generics.presentation.R.drawable.ic_like), contentDescription = "",
                        tint = DuetTheme.colors.secondColor
                    )
                }
            ) {
                listOfNavBarScreens.forEach { navItem ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .noRippleClickable {
                                eventsCutter?.processEvent {
                                    selectedIndex = navItem.id

                                    navController.navigate(navItem.route) {
                                        popUpTo(navController.graph.id) {
                                            saveState = true
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painterResource(id = navItem.idIcon),
                            tint = DuetTheme.colors.backgroundColor,
                            contentDescription = "profile"
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        SetupSubNavGraph(
            navHostController = navController,
            paddingValues = paddingValues
        )
    }
}
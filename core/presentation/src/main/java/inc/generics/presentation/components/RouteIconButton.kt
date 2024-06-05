package inc.generics.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme

data class RouteButtonModel(
    val title: String,
    val additionalText: String? = null,
    val icon: Int? = null
)

@Composable
fun RouteIconButton(
    model: RouteButtonModel,
    onClick: () -> Unit = {},
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .clickable {
                onClick()
            }
    ) {
        if (model.icon != null) {
            Image(modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(8.dp)),
                painter = painterResource(model.icon),
                contentDescription = ""
            )
        } else {
            Box(modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .background(color = DuetTheme.colors.mainColor, shape = RoundedCornerShape(8.dp))
            )
        }

        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 24.dp, end = 24.dp)
        ) {
            Text(text = model.title, style = defaultDialogTextStyleDuet())
            model.additionalText?.let {
                Text(text = it,
                    style = TextStyle(
                        color = DuetTheme.colors.textColor.copy(alpha = 0.5f),
                        fontSize = 12.sp
                    )
                )
            }

        }

        Icon(painter = painterResource(id = R.drawable.ic_arrow_forward),
            tint = DuetTheme.colors.thirdColor,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp)
        )
    }
}

@Composable
fun RouteIconButtonAnimated(
    model: RouteButtonModel,
    onClick: () -> Unit = {},
) {
    var isClick by remember {
        mutableStateOf(false)
    }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .clickable {
                onClick()
                isClick = !isClick
            }
    ) {
        if (model.icon != null) {
            Image(modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(8.dp)),
                painter = painterResource(model.icon),
                contentDescription = ""
            )
        } else {
            Box(modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .background(color = DuetTheme.colors.mainColor, shape = RoundedCornerShape(8.dp))
            )
        }

        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 24.dp, end = 24.dp)
        ) {
            Text(text = model.title, style = defaultDialogTextStyleDuet())
            model.additionalText?.let {
                Text(text = it,
                    style = TextStyle(
                        color = DuetTheme.colors.textColor.copy(alpha = 0.5f),
                        fontSize = 12.sp
                    )
                )
            }

        }

        Icon(painter = painterResource(id = R.drawable.ic_arrow_forward),
            tint = DuetTheme.colors.thirdColor,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp).rotate(animateFloatAsState(
                    if(isClick) 90f else 0f,
                    label = "anim"
                ).value)
        )

    }
}
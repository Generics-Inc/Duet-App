package inc.generics.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme

@Composable
fun DuetAsyncImage(
    roundedDp: Dp = 8.dp,
    painterIconIfNotImg: Painter,
    colorIcon: Color = DuetTheme.colors.mainColor,
    imgUrl: String?,
    size: Dp = 80.dp,
    shadowColor: Color = DefaultShadowColor,
    shadowElevation: Dp = 8.dp,
    offShadow: Boolean = false,
    modifier: Modifier = Modifier.padding(14.dp).size(size).background(
            color = DuetTheme.colors.backgroundColor, shape = RoundedCornerShape(roundedDp)
        )
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier.fillMaxSize().let {
                if (offShadow)
                    it
                else
                    it.shadow(
                    shape = RoundedCornerShape(roundedDp),
                    ambientColor = shadowColor,
                    spotColor = shadowColor,
                    elevation = shadowElevation,
                )
            },
            color = DuetTheme.colors.backgroundColor,
            shape = RoundedCornerShape(roundedDp),
        ) {
            if (imgUrl == null) {
                Icon(
                    painter = painterIconIfNotImg,
                    tint = colorIcon,
                    contentDescription = "icon load image",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                AsyncImage(
                    model = LocalContext.current.getString(R.string.base_url) + imgUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(24.dp).clip(RoundedCornerShape(roundedDp))
                )
            }
        }
    }
}
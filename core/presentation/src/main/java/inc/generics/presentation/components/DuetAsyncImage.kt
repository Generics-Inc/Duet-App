package inc.generics.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    imgUrl: String?,
    modifier: Modifier = Modifier
        .size(80.dp)
        .padding(top = 14.dp)
        .background(
            color = DuetTheme.colors.backgroundColor,
            shape = RoundedCornerShape(roundedDp)
        )
) {
    Box(
        modifier = modifier
    ) {
        if (imgUrl == null) {
            Icon(
                painter = painterIconIfNotImg,
                tint = DuetTheme.colors.mainColor,
                contentDescription = "icon load image",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            AsyncImage(
                model = LocalContext.current.getString(R.string.base_url) + imgUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(roundedDp))
            )
        }
    }
}
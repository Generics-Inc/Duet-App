package inc.generics.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme

@Composable
fun HeadTestAndIcon(text: String, idIcon: Int = R.drawable.ic_heart_broke) {
    Icon(
        painter = painterResource(id = idIcon),
        contentDescription = "broke heart",
        tint = DuetTheme.colors.mainColor,
        modifier = Modifier.padding(top = 100.dp)
    )

    Text(
        text = text,
        style = defaultTextStyleDuet(),
        modifier = Modifier
            .padding(top = 35.dp)
    )
}
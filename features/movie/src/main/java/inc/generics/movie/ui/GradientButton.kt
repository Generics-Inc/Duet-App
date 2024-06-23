package inc.generics.movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.presentation.R
import inc.generics.presentation.theme.DuetTheme

@Composable
fun GradientButtonPaw(
    onClick: () -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(DuetTheme.colors.mainColor, DuetTheme.colors.secondColor),
    )

    FilledTonalButton(
        onClick = { onClick() },
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(brush = gradientBrush, shape = RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_paw),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
                    .align(Alignment.CenterStart),
                tint = DuetTheme.colors.backgroundColor
            )
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = "Самому",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuetTheme.colors.backgroundColor
                )
                Text(
                    text = "Ограничено вашим терпением",
                    fontSize = 12.sp,
                    color = DuetTheme.colors.backgroundColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun GradientButtonHdRezka(
    onClick: () -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(DuetTheme.colors.hdRezkaColor.mainColor, DuetTheme.colors.hdRezkaColor.secondColor),
    )

    FilledTonalButton(
        onClick = { onClick() },
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(brush = gradientBrush, shape = RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterStart),
                text = "HD\nRezka",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DuetTheme.colors.backgroundColor
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = "Из базы фильмов",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuetTheme.colors.backgroundColor
                )
                Text(
                    text = "Пока не ограничено",
                    fontSize = 12.sp,
                    color = DuetTheme.colors.backgroundColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}
package inc.generics.create_new_group

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import inc.generics.presentation.R
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextFieldStyle
import inc.generics.presentation.theme.AppDuetThemeViewModel
import inc.generics.presentation.theme.DuetTheme
import inc.generics.presentation.theme.appDuetThemeViewModel
import inc.generics.presentation.theme.localization.Language

@Composable
fun CreateNewGroupScreen() {
    Scaffold(
        topBar = {
            TitleTopAppBarDuet(
                text = DuetTheme.localization.getString("createGroup"),
                onClickNav = {})
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DuetTheme.colors.backgroundColor)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PhotoBox()
            InputName()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                DefaultFilledTonalButtonDuet(
                    onClick = { /* check inputs and try create groups */ },
                    text = DuetTheme.localization.getString("create"),
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    }
}

internal const val maxLengthName = 40

@SuppressLint("DefaultLocale")
@Composable
internal fun InputName() {
    var text by remember {
        mutableStateOf("")
    } //replace with text from vm
    var isErrorInput by remember {
        mutableStateOf(false)
    }

    TextField(
        value = text,
        onValueChange = { newText ->
            if (newText.length <= maxLengthName) {
                text = newText
                isErrorInput = false
            } else {
                isErrorInput = true
            }
        },
        label = { Text(text = DuetTheme.localization.getString("inputNameGroup")) },
        supportingText = {
            Text(
                text = String.format(
                    DuetTheme.localization.getString("NoMore"),
                    maxLengthName
                )
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(16.dp),
        colors = defaultTextFieldStyle(),
        isError = isErrorInput,
    )
}

@Composable
internal fun PhotoBox(themeViewModel: AppDuetThemeViewModel = appDuetThemeViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable { themeViewModel.setLanguage(Language.Ru()) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_load_img),
            tint = DuetTheme.colors.mainColor,
            contentDescription = "icon load image"
        )
    }
}
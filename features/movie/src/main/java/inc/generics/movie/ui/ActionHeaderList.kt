package inc.generics.movie.ui

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import inc.generics.movie.vm.AddNewMovieBottomSheetViewModel
import inc.generics.movie.vm.MovieFilterViewModel
import inc.generics.presentation.R
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.transparentTextFieldStyle
import inc.generics.presentation.theme.DuetTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun HeaderList(
    viewModelAddNewBottomSheet: AddNewMovieBottomSheetViewModel = koinViewModel(),
    textFilterViewModel: MovieFilterViewModel = koinViewModel()
) {
    var findMode by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(DuetTheme.colors.thirdColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp)
    ) {
        AnimatedContent(
            targetState = findMode,
            transitionSpec = {
                expandHorizontally() + fadeIn() togetherWith
                        shrinkHorizontally() + fadeOut() using
                        SizeTransform(clip = false)
            },
            label = ""
        ) { targetState ->
            if (targetState) {
                Row {
                    OutlinedButtonDuet(
                        modifier = Modifier.weight(1f),
                        hasBorder = false,
                        hasElevation = false,
                        containerColor = DuetTheme.colors.thirdColor,
                        contentColor = DuetTheme.colors.backgroundColor,
                        onClick = { findMode = false }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.ic_find),
                            contentDescription = "",
                            tint = DuetTheme.colors.backgroundColor
                        )
                    }

                    OutlinedButtonDuet(
                        modifier = Modifier.weight(1f),
                        hasBorder = false,
                        hasElevation = false,
                        containerColor = DuetTheme.colors.thirdColor,
                        contentColor = DuetTheme.colors.backgroundColor,
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.ic_filter),
                            contentDescription = "",
                            tint = DuetTheme.colors.backgroundColor
                        )
                    }

                    OutlinedButtonDuet(
                        modifier = Modifier.weight(1f),
                        hasBorder = false,
                        hasElevation = false,
                        containerColor = DuetTheme.colors.thirdColor,
                        contentColor = DuetTheme.colors.backgroundColor,
                        onClick = { viewModelAddNewBottomSheet.show() }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = "",
                            tint = DuetTheme.colors.backgroundColor
                        )
                    }
                }
            } else {
                AutoFocusTextField(
                    onClickIcon = {
                        findMode = true
                        textFilterViewModel.cleanTextFilter()
                    }
                )
            }
        }
    }
}

@OptIn(FlowPreview::class)
@Composable
fun AutoFocusTextField(
    modifier: Modifier = Modifier, onClickIcon: () -> Unit,
    textFilterViewModel: MovieFilterViewModel = koinViewModel()
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val textChangeFlow = remember { MutableStateFlow("") }


    LaunchedEffect(Unit) {
        // Adding a small delay to ensure the UI is fully loaded before requesting focus
        delay(100)
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    LaunchedEffect(textChangeFlow) {
        textChangeFlow
            .debounce(400)
            .collectLatest {
                Log.d("textChangeFlow", "seet ${textState.value.text}")
                textFilterViewModel.setTextFilter(textState.value.text)
            }
    }

    Column(modifier = modifier) {
        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                textChangeFlow.value = it.text
            },
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester),
            colors = transparentTextFieldStyle(),
            maxLines = 1,
            singleLine = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    modifier = Modifier
                        .clip(RoundedCornerShape(2.dp))
                        .clickable {
                            onClickIcon()
                        },
                    contentDescription = "",
                    tint = DuetTheme.colors.backgroundColor
                )
            }
        )
    }
}
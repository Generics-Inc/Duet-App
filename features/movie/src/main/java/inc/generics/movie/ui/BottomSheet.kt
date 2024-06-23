package inc.generics.movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inc.generics.movie.vm.ActionItemBottomSheetViewModel
import inc.generics.movie.vm.AddNewMovieBottomSheetViewModel
import inc.generics.movie.vm.MovieViewModel
import inc.generics.presentation.components.FilledTonalButtonDuet
import inc.generics.presentation.components.OutlinedButtonDuet
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.components.defaultTextStyleForButtonDuet
import inc.generics.presentation.components.secondTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SetupActionItemBottomSheet(
    viewModel: MovieViewModel = koinViewModel(),
    viewModelBottomSheetViewModel: ActionItemBottomSheetViewModel = koinViewModel()
) {
    val movieData by viewModelBottomSheetViewModel.dataItem.observeAsState()

    if (movieData == null) {
        viewModelBottomSheetViewModel.dismiss()
        return
    }

    ModalBottomSheet(
        onDismissRequest = {
            viewModelBottomSheetViewModel.dismiss()
        },
        containerColor = DuetTheme.colors.backgroundColor,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(
                        width = 90.dp,
                        height = 6.dp
                    )
                    .background(DuetTheme.colors.mainColor, RoundedCornerShape(2.dp))
            )
        },
        scrimColor = Color.Gray.copy(alpha = 0.7f)
    ) {
        Column(
            Modifier.fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 22.dp
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButtonDuet(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    },
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = "Подробнее",
                        style = defaultTextStyleForButtonDuet()
                    )
                }

                FilledTonalButtonDuet(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.deleteById(id = movieData!!.id)
                        viewModelBottomSheetViewModel.dismiss()
                    },
                    color = DuetTheme.colors.errorColor
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = "Удалить",
                        style = defaultTextStyleForButtonDuet()
                    )
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                text = "Выбери действие для \"${movieData?.name ?: ""}\"",
                style = secondTextStyleDuet()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupAddNewMovieBottomSheet(
    viewModelAddNewBottomSheet: AddNewMovieBottomSheetViewModel = koinViewModel()
) {
    ModalBottomSheet(
        onDismissRequest = {
            viewModelAddNewBottomSheet.dismiss()
        },
        containerColor = DuetTheme.colors.backgroundColor,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(
                        width = 90.dp,
                        height = 6.dp
                    )
                    .background(DuetTheme.colors.mainColor, RoundedCornerShape(2.dp))
            )
        },
        scrimColor = Color.Gray.copy(alpha = 0.7f)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Добавить запись",
                style = defaultTextStyleDuet().copy(fontSize = 14.sp)
            )
            GradientButtonPaw(onClick = {})
            GradientButtonHdRezka(onClick = {})
        }
    }
}
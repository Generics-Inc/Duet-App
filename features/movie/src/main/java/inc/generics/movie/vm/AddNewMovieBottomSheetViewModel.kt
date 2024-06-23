package inc.generics.movie.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.generics.movie.vm.models.StateBottomSheetViewModel

class AddNewMovieBottomSheetViewModel : ViewModel() {
    private val _state = MutableLiveData(StateBottomSheetViewModel.DISMISS)
    val state: LiveData<StateBottomSheetViewModel> = _state

    fun show() {
        _state.value = StateBottomSheetViewModel.SHOW
    }

    fun dismiss() {
        _state.value = StateBottomSheetViewModel.DISMISS
    }
}
package inc.generics.movie.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.generics.movie.vm.models.StateBottomSheetViewModel

class ActionItemBottomSheetViewModel : ViewModel() {
    private val _state = MutableLiveData(StateBottomSheetViewModel.DISMISS)
    val state: LiveData<StateBottomSheetViewModel> = _state

    private val _dataItem: MutableLiveData<DataItem> = MutableLiveData(null)
    val dataItem: LiveData<DataItem> = _dataItem

    fun show(data: DataItem) {
        _dataItem.value = data
        _state.value = StateBottomSheetViewModel.SHOW
    }

    fun dismiss() {
        _state.value = StateBottomSheetViewModel.DISMISS
    }
}

data class DataItem(
    val id: Long,
    val name: String
)
package inc.generics.movie.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActionItemBottomSheetViewModel : ViewModel() {
    private val _state = MutableLiveData(StateActionItemBottomSheetViewModel.DISMISS)
    val state: LiveData<StateActionItemBottomSheetViewModel> = _state

    private val _dataItem: MutableLiveData<DataItem> = MutableLiveData(null)
    val dataItem: LiveData<DataItem> = _dataItem

    fun show(data: DataItem) {
        _dataItem.value = data
        _state.value = StateActionItemBottomSheetViewModel.SHOW
    }

    fun dismiss() {
        _state.value = StateActionItemBottomSheetViewModel.DISMISS
    }
}

data class DataItem(
    val id: Long,
    val name: String
)

enum class StateActionItemBottomSheetViewModel{
    SHOW,
    DISMISS
}
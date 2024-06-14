package inc.generics.archive.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.archive_data.ArchiveRepository
import inc.generics.archive_data.models.ArchiveItem
import kotlinx.coroutines.launch

class ArchiveViewModel(
    private val repository: ArchiveRepository
) : ViewModel() {
    private val _listOfArchive: MutableLiveData<List<ArchiveItem>> = MutableLiveData(emptyList())
    val listOfArchive: LiveData<List<ArchiveItem>> = _listOfArchive

    private val _statusLoadingList = MutableLiveData(LoadStatus.NONE)
    val statusLoadingList: LiveData<LoadStatus> = _statusLoadingList

    private val _statusAction = MutableLiveData(ActionStatus.NONE)
    val statusAction: LiveData<ActionStatus> = _statusAction

    fun getArchive() = viewModelScope.launch {
        _statusLoadingList.value = LoadStatus.LOAD

        repository.allArchive()?.let {
            _listOfArchive.value = it
            _statusLoadingList.value = LoadStatus.FINISH
            return@launch
        }

        _statusLoadingList.value = LoadStatus.ERROR
    }

    fun deleteWithUpdateUiData(id: Long) = viewModelScope.launch {
        updateUiDataByStatus(
            repository.delete(id),
            isRevert = false
        )
    }

    fun revertWithUpdateUiData(id: Long) = viewModelScope.launch {
        updateUiDataByStatus(
            repository.revert(id),
            isRevert = true
        )
    }

    private fun updateUiDataByStatus(isActionSuccess: Boolean, isRevert: Boolean) {
        getArchive()
        if (isActionSuccess)
            _statusAction.value = if (isRevert) ActionStatus.SUCCESS_REVERT else ActionStatus.SUCCESS_DELETE
        else
            _statusAction.value = ActionStatus.ERROR
    }

    fun resetActionStatus() {
        _statusAction.value = ActionStatus.NONE
    }

    fun resetLoadStatus() {
        _statusLoadingList.value = LoadStatus.NONE
    }
}

enum class ActionStatus {
    NONE,
    ERROR,
    SUCCESS_REVERT,
    SUCCESS_DELETE,
}

enum class LoadStatus {
    NONE,
    ERROR,
    LOAD,
    FINISH,
}
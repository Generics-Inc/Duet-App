package inc.generics.group.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.group_data.GroupRepository
import inc.generics.group_data.models.Group
import kotlinx.coroutines.launch

class GroupViewModel(
    private val repository: GroupRepository
) : ViewModel() {
    private val _dataScreen: MutableLiveData<Group?> = MutableLiveData(null)
    val dataScreen: LiveData<Group?> = _dataScreen

    private val _screenStatus = MutableLiveData(ScreenStatus.NONE)
    val screenStatus: LiveData<ScreenStatus> = _screenStatus

    fun getGroup() = viewModelScope.launch {
        repository.getCurrentGroup()?.let {
            _dataScreen.value = it
        } ?: onError()
    }

    fun leaveGroup() = viewModelScope.launch {
        repository.deleteGroup().let {
            if (it)
                _screenStatus.value = ScreenStatus.ON_LEAVE
            else
                onError()
        }
    }

    private fun onError() {
        _screenStatus.value = ScreenStatus.NETWORK_ERROR
    }
}

enum class ScreenStatus {
    NONE,
    NETWORK_ERROR,
    ON_LEAVE
}
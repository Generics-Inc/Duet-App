package inc.generics.create_new_group

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.create_new_group.models.Group
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateNewGroupViewModel(private val repository: CreateNewGroupRepository) : ViewModel() {
    private val _screenState: MutableLiveData<State> = MutableLiveData(State())
    val screenState: LiveData<State> = _screenState

    private val _statusCreateGroup: MutableLiveData<StatusCreateGroup> = MutableLiveData(
        StatusCreateGroup.NONE
    )
    val statusCreateGroup: LiveData<StatusCreateGroup> = _statusCreateGroup

    fun createGroup() {
        if (!checkNameGroup())
            return

        _statusCreateGroup.value = StatusCreateGroup.IN_PROCESS
        viewModelScope.launch {
            delay(2000)
            val result = repository.create(
                Group(
                    _screenState.value?.name!!,
                    _screenState.value?.photoUri
                )
            )
            if (result) {
                _statusCreateGroup.value = StatusCreateGroup.FINISH
            } else {
                _statusCreateGroup.value = StatusCreateGroup.ERROR
            }
        }
    }

    fun pickPhoto(uri: Uri) {
        _screenState.value = _screenState.value!!.copy(photoUri = uri)
    }

    fun setNoneInStatusCreate() {
        _statusCreateGroup.value = StatusCreateGroup.NONE
    }

    fun setNameGroup(name: String) {
        _screenState.value = _screenState.value!!.copy(name = name)
    }

    private fun checkNameGroup(): Boolean {
        return if (_screenState.value?.name.isNullOrEmpty()) {
            _statusCreateGroup.value = StatusCreateGroup.ERROR_START_CREATE_EMPTY_NAME
            false
        } else true
    }
}

data class State(
    val photoUri: Uri? = null,
    val name: String? = null
)

enum class StatusCreateGroup {
    NONE,
    IN_PROCESS,
    ERROR_START_CREATE_EMPTY_NAME,
    ERROR,
    FINISH
}
package inc.generics.join_to_group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.join_to_group_data.JoinToGroupRepository
import kotlinx.coroutines.launch

class JoinToGroupViewModel(
    private val repository: JoinToGroupRepository
) : ViewModel() {
    private val _statusInviteCode = MutableLiveData(StatusInviteCode.NONE)
    val statusInviteCode: LiveData<StatusInviteCode> = _statusInviteCode

    fun sendInviteCode(inviteCode: String) = viewModelScope.launch {
        if (checkInviteCode(inviteCode)) {
            setSendToStatus()
            setFinishOrError(repository.joinToGroupByInviteCode(inviteCode))
        }
    }

    fun setNoneToStatusInviteCode() {
        _statusInviteCode.value = StatusInviteCode.NONE
    }

    private fun setSendToStatus() {
        _statusInviteCode.value = StatusInviteCode.SEND
    }

    private fun setFinishOrError(resultFlag: Boolean) {
        _statusInviteCode.value = when(resultFlag) {
            true -> StatusInviteCode.FINISH
            false -> StatusInviteCode.ERROR
        }
    }

    private fun checkInviteCode(inviteCode: String): Boolean {
        return if (inviteCode.isEmpty()) {
            _statusInviteCode.value = StatusInviteCode.EMPTY
            false
        } else true
    }
}

enum class StatusInviteCode {
    NONE,
    EMPTY,
    SEND,
    FINISH,
    ERROR
}
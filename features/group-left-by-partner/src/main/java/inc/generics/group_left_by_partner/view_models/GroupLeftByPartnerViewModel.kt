package inc.generics.group_left_by_partner.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.group_left_by_partner_data.GroupLeftByPartnerRepository
import kotlinx.coroutines.launch

class GroupLeftByPartnerViewModel(
    private val repository: GroupLeftByPartnerRepository
) : ViewModel() {
    private val _stateScreen = MutableLiveData(StateScreen.NONE)
    val stateScreen: LiveData<StateScreen> = _stateScreen

    fun deletePartner() = viewModelScope.launch {
        repository.deletePartner().let {
            if (it) _stateScreen.value = StateScreen.DELETE_PARTNER
        }
    }

    fun leaveGroup() = viewModelScope.launch {
        repository.leaveGroup().let {
            if (it) _stateScreen.value = StateScreen.LEAVE_GROUP
        }
    }

    fun newInviteCode() = viewModelScope.launch {
        _stateScreen.value = StateScreen.NEW_INVITE_CODE_IN_PROCESS
        repository.createNewInviteCode().let {
            _stateScreen.value = if (it)
                 StateScreen.NEW_INVITE_CODE_IS_GENERATED
            else
                StateScreen.NONE
        }
    }
}

enum class StateScreen {
    NONE,
    DELETE_PARTNER,
    LEAVE_GROUP,
    NEW_INVITE_CODE_IN_PROCESS,
    NEW_INVITE_CODE_IS_GENERATED
}

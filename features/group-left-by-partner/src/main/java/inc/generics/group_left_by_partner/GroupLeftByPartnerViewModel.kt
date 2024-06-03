package inc.generics.group_left_by_partner

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
}

enum class StateScreen {
    NONE,
    DELETE_PARTNER,
    LEAVE_GROUP
}
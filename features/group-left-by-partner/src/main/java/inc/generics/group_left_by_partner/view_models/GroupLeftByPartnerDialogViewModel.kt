package inc.generics.group_left_by_partner.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupLeftByPartnerDialogViewModel : ViewModel() {
    private val _stateDialogs = MutableLiveData(StateDialogs.NO_DIALOG)
    val stateDialogs: LiveData<StateDialogs> = _stateDialogs
    fun tryDeletePartner() {
        _stateDialogs.value = StateDialogs.TRY_DELETE_PARTNER
    }

    fun tryLeaveGroup() {
        _stateDialogs.value = StateDialogs.TRY_LEAVE_GROUP
    }

    fun dismissDialog() {
        _stateDialogs.value = StateDialogs.NO_DIALOG
    }
}

enum class StateDialogs {
    NO_DIALOG,
    TRY_DELETE_PARTNER,
    TRY_LEAVE_GROUP,
}
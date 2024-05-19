package inc.generics.group_without_partner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.group_without_partner.models.GroupWithoutPartner
import kotlinx.coroutines.launch

class GroupWithoutPartnerViewModel(
    private val repository: GroupWithoutPartnerRepository
) : ViewModel() {
    private val _loadStatus = MutableLiveData(LoadStatus.NONE)
    val loadStatus: LiveData<LoadStatus> = _loadStatus

    private val _groupWithoutPartner: MutableLiveData<GroupWithoutPartner> =
        MutableLiveData(GroupWithoutPartner("", "", null))
    val groupWithoutPartner: LiveData<GroupWithoutPartner> = _groupWithoutPartner

    private val _isLeaved: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLeaved: LiveData<Boolean> = _isLeaved

    fun leaveGroup() = viewModelScope.launch {
        _loadStatus.value = if (!repository.leaveGroup()) LoadStatus.ERROR else {
            _isLeaved.value = true
            LoadStatus.SUCCESS
        }
    }

    fun getGroup() = viewModelScope.launch {
        repository.getGroup()?.let {
            _groupWithoutPartner.value = it
            _loadStatus.value = LoadStatus.SUCCESS
            return@launch
        }
        _loadStatus.value = LoadStatus.ERROR
        return@launch
    }
}

enum class LoadStatus {
    NONE,
    ERROR,
    SUCCESS
}
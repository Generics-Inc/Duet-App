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

    fun getGroup() = viewModelScope.launch {
        _dataScreen.value = repository.getCurrentGroup()
    }
}
package inc.generics.duet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.duet_api.api.DuetApi
import kotlinx.coroutines.launch

class MainViewModel(private val api: DuetApi) : ViewModel() {
    private val _statusGroup: MutableLiveData<StatusGroup> = MutableLiveData(StatusGroup.NONE)
    val statusGroup: LiveData<StatusGroup> = _statusGroup
    fun refreshTokens() = viewModelScope.launch {
        api.refreshToken()
    }

    fun checkGroupStatus() = viewModelScope.launch {
    }
}

enum class StatusGroup {
    NOT_ACTIVE_GROUP,
    NOT_AUTHORIZE,
    ACTIVE_GROUP,
    NONE
}
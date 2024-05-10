package inc.generics.duet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.util.DuetHttpException
import inc.generics.duet_api.util.toDuetHttpException
import kotlinx.coroutines.launch

class MainViewModel(private val api: DuetApi) : ViewModel() {
    private val _statusGroup: MutableLiveData<StatusGroup> = MutableLiveData(StatusGroup.NONE)
    val statusGroup: LiveData<StatusGroup> = _statusGroup
    fun refreshTokens() = viewModelScope.launch {
        api.refreshToken()
        Log.d("refreshToken", "refreshToken")
    }

    fun checkGroupStatus() = viewModelScope.launch {
        val result = api.getStatusUsersInGroup()
        result.onFailure {
            when(toDuetHttpException(it)) {
                DuetHttpException.UNAUTHORIZED -> {
                    _statusGroup.value = StatusGroup.NOT_AUTHORIZE
                }
                else -> {
                    //что-то пошло не так
                    StatusGroup.NONE
                }
            }
        }
    }
}

enum class StatusGroup {
    NOT_AUTHORIZE,
    NOT_ACTIVE_GROUP,
    IN_GROUP__NO_PARTNER,
    IN_GROUP__PARTNER_LEAVED,
    ACTIVE_GROUP,
    NONE
}
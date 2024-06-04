package inc.generics.duet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.models.profile.PartnerStatusInGroup
import inc.generics.duet_api.models.profile.UserStatusInGroup
import inc.generics.duet_api.util.DuetHttpException
import inc.generics.duet_api.util.toDuetHttpException
import inc.generics.duet_local.sp.SPHelper
import kotlinx.coroutines.launch

// на рефакторинг можно вынести в репозиторий
class MainViewModel(private val api: DuetApi, private val spHelper: SPHelper) : ViewModel() {
    private val _statusGroup: MutableLiveData<StatusGroup> = MutableLiveData(StatusGroup.None)
    val statusGroup: LiveData<StatusGroup> = _statusGroup
    fun refreshTokens() = viewModelScope.launch {
        //НА РЕФАКТОРИНГ ВЫЗВАТЬ ОТДЕЛЬНО И ВОЗВРАЩАТЬ СТАТУС
        // после статуса FINISH вызывать checkGroupStatus()
    }

    fun checkGroupStatus() = viewModelScope.launch {
        val result1 = api.refreshToken()
        result1.onSuccess {
            spHelper.refreshToken = it.refreshToken
            spHelper.accessToken = it.accessToken
        }

        val result = api.getStatusUsersInGroup()
        result.onFailure {
            when(toDuetHttpException(it)) {
                DuetHttpException.UNAUTHORIZED -> {
                    _statusGroup.value = StatusGroup.NotAuthorize
                }
                else -> {
                    //что-то пошло не так
                }
            }
        }

        result.onSuccess {
            val userStatus = it.getSelfStatus()
            val parentStatus = it.getPartnerStatus()

            when(userStatus) {
                UserStatusInGroup.NOT_IN_GROUP -> {
                    _statusGroup.value = StatusGroup.NotActiveGroup(false)
                }
                UserStatusInGroup.NOT_IN_GROUP_WITH_ARCHIVE -> {
                    _statusGroup.value = StatusGroup.NotActiveGroup(true)
                }
                UserStatusInGroup.IN_GROUP -> {
                    when(parentStatus) {
                        PartnerStatusInGroup.NO_PARTNER -> {
                            _statusGroup.value = StatusGroup.InGroupNoPartner
                        }
                        PartnerStatusInGroup.GROUP_IN_ARCHIVE -> {
                            _statusGroup.value = StatusGroup.InGroupPartnerLeaved(
                                false,
                                it.isMainInGroup
                            )
                        }
                        PartnerStatusInGroup.LEAVED -> {
                            _statusGroup.value = StatusGroup.InGroupPartnerLeaved(
                                true,
                                it.isMainInGroup
                            )
                        }
                        PartnerStatusInGroup.IN_GROUP -> {}
                        null -> TODO()
                    }
                }
                null -> {}
            }
        }
    }

    //todo: delete method. it is for a test
    fun leaveGroup() = viewModelScope.launch {
        api.leaveGroup()
    }
}

sealed class StatusGroup {
    data object NotAuthorize: StatusGroup()
    data class NotActiveGroup(val hashArchive: Boolean): StatusGroup()
    data object InGroupNoPartner: StatusGroup()
    data class InGroupPartnerLeaved(val isPartnerLeaved: Boolean, val isMainInGroup: Boolean): StatusGroup()
    data object ActiveGroup: StatusGroup()
    data object None: StatusGroup()
}
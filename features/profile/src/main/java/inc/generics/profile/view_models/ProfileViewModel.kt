package inc.generics.profile.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.profile_data.ProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _screenState = MutableLiveData<ScreenState?>(null)
    val screenState: LiveData<ScreenState?> = _screenState

    fun loadInformationAboutProfile() = viewModelScope.launch {
        delay(2500)
        _screenState.value = ScreenState(
            fullName = "Владислва Илларионов",
            status = "побольшей части это ложь, разбавленная слухами и домаслами",
            groupInfo = GroupInfo(
                nameGroup = "Название группы",
                namePartner = null,
                isPartnerInGroup = false,
                partnerPhotoUrl = null,
                groupPhotoUrl = null
            ),
            accountsList = listOf(
                UserConnectedAccount(
                    username = "@illarionov887",
                    typeAccount = TypeAccount.VK
                )
            ),
            userPhotoUrl = null
        )
    }

    fun logout() {
        repository.logout()
    }

}

data class ScreenState(
    val fullName: String,
    val status: String?,
    val userPhotoUrl: String?,
    val groupInfo: GroupInfo?,
    val accountsList: List<UserConnectedAccount> = emptyList()
)

data class GroupInfo(
    val nameGroup: String,
    val namePartner: String?,
    val groupPhotoUrl: String?,
    val partnerPhotoUrl: String?,
    val isPartnerInGroup: Boolean
)

data class UserConnectedAccount(
    val username: String,
    val typeAccount: TypeAccount
)

enum class TypeAccount {
    VK, TG, NONE
}
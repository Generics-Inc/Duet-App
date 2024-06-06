package inc.generics.profile.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.profile_data.ProfileRepository
import inc.generics.profile_data.models.GroupInfo
import inc.generics.profile_data.models.ProfileInfo
import inc.generics.profile_data.models.TypeAccount
import inc.generics.profile_data.models.UserConnectedAccount
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _screenState = MutableLiveData<ProfileInfo?>(null)
    val screenState: LiveData<ProfileInfo?> = _screenState

    fun loadInformationAboutProfile() = viewModelScope.launch {
        repository.getProfileInformation()?.let {
            _screenState.value = it
        } // todo: показывать ошибку в диалоге
    }

    fun logout() {
        repository.logout()
    }

}


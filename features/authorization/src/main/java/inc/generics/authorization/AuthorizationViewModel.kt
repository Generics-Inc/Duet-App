package inc.generics.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.authorization_data.AuthorizationRepository
import kotlinx.coroutines.launch

class AuthorizationViewModel(private val repository: AuthorizationRepository) : ViewModel() {
    private val _authorizationStatus: MutableLiveData<AuthStatus> = MutableLiveData(AuthStatus.NoAuthorized)
    val authorizationStatus: LiveData<AuthStatus> = _authorizationStatus
    fun toAuthorizeByVk(accessToke: String) {
        _authorizationStatus.value = AuthStatus.InProcess
        viewModelScope.launch {
            val result = repository.authViaVk(accessToke)
            _authorizationStatus.value = if(result) AuthStatus.Successes else AuthStatus.Error
        }
    }
}

enum class AuthStatus {
    NoAuthorized, InProcess, Error, Successes
}
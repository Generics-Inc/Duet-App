package inc.generics.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.requests_data.RequestsRepository
import kotlinx.coroutines.launch

class RequestsViewModel(
    private val repository: RequestsRepository
) : ViewModel() {
    private val _statusLoading = MutableLiveData(StatusLoading.NONE)
    val statusLoading: LiveData<StatusLoading> = _statusLoading

    private val _listOfRequests: MutableLiveData<List<RequestUi>> = MutableLiveData(emptyList())
    val listOfRequest: LiveData<List<RequestUi>> = _listOfRequests

    private val _hasAcceptRequest = MutableLiveData(false)
    val hasAcceptRequest: LiveData<Boolean> = _hasAcceptRequest

    fun getAllRequests() = viewModelScope.launch {
        _statusLoading.value = StatusLoading.LOADING
        val requestsUi = repository.allRequest()?.run {
            this.map {
                RequestUi(
                    it.id,
                    (it.firstName ?: "") + " " + (it.lastName ?: ""),
                    it.photoUrl
                )
            }
        }

        _statusLoading.value = requestsUi.run {
            if (this == null)
                StatusLoading.ERROR
            else {
                _listOfRequests.value = this
            }
            StatusLoading.FINISH
        }
    }

    fun acceptRequest(id: Long) = viewModelScope.launch {
        _hasAcceptRequest.value = repository.accept(id)
        // here set flag error for dialog
    }

    fun cancelRequest(id: Long) = viewModelScope.launch {
        repository.cancel(id).let {
            if (it) {
                getAllRequests()
            }
        }
        // here set flag error for dialog
    }
}

enum class StatusLoading {
    NONE,
    LOADING,
    FINISH,
    ERROR
}

data class RequestUi(
    val id: Long,
    val name: String,
    val photoUrl: String?
)
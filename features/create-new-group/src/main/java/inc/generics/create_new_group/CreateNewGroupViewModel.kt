package inc.generics.create_new_group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.create_new_group.interactors.CreateNewGroupInteractor
import inc.generics.domain_models.models.GroupSimple
import kotlinx.coroutines.launch

class CreateNewGroupViewModel(private val interactor: CreateNewGroupInteractor) : ViewModel() {
    companion object {
        data class State(
            val isGroupCreated: Boolean = false,
            val photoPath: String? = null,
            val name: String? = null
        )
    }

    private val _screenState: MutableLiveData<State> = MutableLiveData(State())
    val screenState: LiveData<State> = _screenState
    fun createGroup(newGroup: GroupSimple) {
        viewModelScope.launch {
            interactor.create(newGroup)
            onGroupCreated() //если группа успешно создалась
        }
    }

    fun pickPhoto(path: String) {
        _screenState.value = _screenState.value!!.copy(photoPath = path)
    }

    private fun onGroupCreated() {
        _screenState.value = _screenState.value!!.copy(isGroupCreated = true)
    }
}
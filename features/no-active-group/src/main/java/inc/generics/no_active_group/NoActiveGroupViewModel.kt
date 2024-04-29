package inc.generics.no_active_group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.generics.no_active_group.interactors.NoActiveGroupInteractor

class NoActiveGroupViewModel(
    private val noActiveGroupInteractor: NoActiveGroupInteractor
): ViewModel() {
    private val _isOpenDeleteDialog: MutableLiveData<Boolean> = MutableLiveData(false)
    val isOpenDeleteDialog: LiveData<Boolean> = _isOpenDeleteDialog

    fun saveArchiveThisGroup() {
        noActiveGroupInteractor.saveArchive()
    }

    fun deleteGroup() {
        noActiveGroupInteractor.deleteGroup()
    }

    fun openDeleteDialog() {
        _isOpenDeleteDialog.value = true
    }

    fun closeDeleteDialog() {
        _isOpenDeleteDialog.value = false
    }

}
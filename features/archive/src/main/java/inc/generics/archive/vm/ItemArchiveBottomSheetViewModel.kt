package inc.generics.archive.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.generics.archive_data.models.ArchiveItem

class ItemArchiveBottomSheetViewModel: ViewModel() {
    private val _isShow = MutableLiveData(false)
    val isShow: LiveData<Boolean> = _isShow

    private val _data: MutableLiveData<ArchiveItem?> = MutableLiveData(null)
    val data: LiveData<ArchiveItem?> = _data

    fun show(data: ArchiveItem) {
        _data.value = data
        _isShow.value = true
    }

    fun hide() {
        _isShow.value = false
    }
}

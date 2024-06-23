package inc.generics.movie.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieFilterViewModel : ViewModel() {
    private val _textFilter = MutableLiveData("")
    val textFilter: LiveData<String> = _textFilter

    fun setTextFilter(text: String) {
        _textFilter.value = text
    }

    fun cleanTextFilter() {
        _textFilter.value = ""
    }
}
package inc.generics.new_movie_hdrezka.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.new_movie_hdrezka.NewMovieRepository
import inc.generics.new_movie_hdrezka.models.CreateMovieHdRezka
import inc.generics.new_movie_hdrezka.models.ItemSearch
import kotlinx.coroutines.launch

class NewMovieHdRezkaViewModel(
    private val repository: NewMovieRepository
) : ViewModel() {
    private val _searchList: MutableLiveData<List<ItemSearch>?> = MutableLiveData(null)
    val searchList: LiveData<List<ItemSearch>?> = _searchList

    fun createMovie(createMovieHdRezka: CreateMovieHdRezka) = viewModelScope.launch {
        repository.createByLink(createMovieHdRezka)
    }

    fun search(searchText: String) = viewModelScope.launch {
        repository.searchMovie(searchText)?.let {
            _searchList.value = it
        }
    }
}
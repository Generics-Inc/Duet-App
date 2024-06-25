package inc.generics.movie_in_detail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.movie_in_detail_data.MovieInDetailRepository
import inc.generics.movie_in_detail_data.models.CreateMovieHdRezka
import inc.generics.movie_in_detail_data.models.MovieInDetail
import kotlinx.coroutines.launch

class MovieInDetailViewModel(
    private val repository: MovieInDetailRepository,
) : ViewModel() {
    private val _dataScreen: MutableLiveData<MovieInDetail> = MutableLiveData(null)
    val dataScreen: LiveData<MovieInDetail> = _dataScreen

    private val _screenState = MutableLiveData(ScreenState.NONE)
    val screenState: LiveData<ScreenState> = _screenState

    fun getMovie(id: Long) = viewModelScope.launch {
        repository.getMovie(id)?.let {
            _dataScreen.value = it
        }
    }

    fun deleteMovie(id: Long) = viewModelScope.launch {
        if (repository.deleteMovie(id)) {
            _screenState.value = ScreenState.DELETE_CURRENT_MOVIE
        } else {
            _screenState.value = ScreenState.ERROR
        }
    }

    fun createByLink(createMovieHdRezka: CreateMovieHdRezka) = viewModelScope.launch {
        repository.addMovieByLink(createMovieHdRezka)
    }
}

enum class ScreenState {
    NONE,
    ERROR,
    DELETE_CURRENT_MOVIE
}
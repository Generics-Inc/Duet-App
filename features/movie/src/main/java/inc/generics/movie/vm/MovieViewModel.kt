package inc.generics.movie.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.generics.movie_data.MovieRepository
import inc.generics.movie_data.models.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository): ViewModel() {
    private val _listOfMovie: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    val listOfMovie: LiveData<List<Movie>> = _listOfMovie

    private val _statusScreen = MutableLiveData(StatusScreen.NONE)
    val statusScreen: LiveData<StatusScreen> = _statusScreen


    fun getAllMovies() = viewModelScope.launch {
        _statusScreen.value = StatusScreen.LOAD

        repository.getAllMovies()?.let {
            _listOfMovie.value = it
            _statusScreen.value = StatusScreen.FINISH
            return@launch
        }

        _statusScreen.value = StatusScreen.ERROR
    }

    fun deleteById(id: Long) = viewModelScope.launch {
        if (repository.delete(id)) {
            getAllMovies() //todo пока нет сокетов
        }
    }

    fun tryAgan(taskId: Long) = viewModelScope.launch {
        if(repository.tryCreateAganByTaskId(taskId)) {
            getAllMovies()
        }
    }
}

enum class StatusScreen {
    LOAD,
    NONE,
    FINISH,
    ERROR
}
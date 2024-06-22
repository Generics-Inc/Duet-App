package inc.generics.movie_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.movie_data.models.CreateMovieHdRezka
import inc.generics.movie_data.models.Movie
import inc.generics.utils_data.exceptions.safeRequest

class MovieRepository(
    private val api: DuetApi
) {
    suspend fun getAllMovies(): List<Movie>? {
        api.getAllMovies().onSuccess {
            return it.map { item -> item.toUi() }
        }
        return null
    }

    suspend fun createByLink(createMovieHdRezka: CreateMovieHdRezka): Boolean {
        return api.crateMovie(createMovieHdRezka.toDto()).isSuccess
    }

    suspend fun delete(id: Long): Boolean {
        return api.deleteMovie(id).isSuccess
    }

    suspend fun tryCreateAganByTaskId(taskId: Long) = safeRequest {
        api.createMovieAgainByTaskId(taskId)
    }
}
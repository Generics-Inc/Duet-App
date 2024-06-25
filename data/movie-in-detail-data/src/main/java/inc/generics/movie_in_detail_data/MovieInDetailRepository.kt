package inc.generics.movie_in_detail_data

import android.util.Log
import inc.generics.duet_api.api.DuetApi
import inc.generics.movie_in_detail_data.models.CreateMovieHdRezka
import inc.generics.movie_in_detail_data.models.MovieInDetail

class MovieInDetailRepository(
    private val api: DuetApi
) {
    suspend fun getMovie(id: Long): MovieInDetail? {
        api.getMovieById(id).onSuccess {
            return it.toUi()
        }.onFailure {
            Log.e("getMovie", it.toString())
        }

        return null
    }

    suspend fun setWatchedFlag(id: Long): Boolean {
        return api.setWatchedFlagMovieById(id).isSuccess
    }

    suspend fun deleteMovie(id: Long): Boolean {
        return api.deleteMovie(id).isSuccess
    }

    suspend fun addMovieByLink(createMovieHdRezka: CreateMovieHdRezka): Boolean {
        return api.crateMovie(createMovieHdRezka.toDto()).isSuccess
    }

}
package inc.generics.movie_in_detail_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.movie_in_detail_data.models.CreateMovieHdRezka
import inc.generics.movie_in_detail_data.models.MovieInDetail
import kotlinx.coroutines.delay

class MovieInDetailRepository(
    private val api: DuetApi
) {
    suspend fun getMovie(id: Long): MovieInDetail? {
        delay(100)
        return MovieInDetail(
            id = 0,
            photoUrl = null,
            name = "Название филма",
            originalName = "names movie",
            genres = listOf(
                "Боевик" , "Ужасы"
            ),
            isWatch = false,
            description = "",
            parts = listOf(
            )
        )
        //todo: for tests
    }

    suspend fun deleteMovie(id: Long): Boolean {
        return api.deleteMovie(id).isSuccess
    }

    suspend fun addMovieByLink(createMovieHdRezka: CreateMovieHdRezka): Boolean {
        return api.crateMovie(createMovieHdRezka.toDto()).isSuccess
    }

}
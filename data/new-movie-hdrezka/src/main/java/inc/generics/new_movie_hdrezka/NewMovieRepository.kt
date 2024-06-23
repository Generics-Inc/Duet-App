package inc.generics.new_movie_hdrezka

import inc.generics.duet_api.api.DuetApi
import inc.generics.new_movie_hdrezka.models.CreateMovieHdRezka

class NewMovieRepository(private val api: DuetApi) {
    suspend fun createByLink(createMovieHdRezka: CreateMovieHdRezka): Boolean {
        return api.crateMovie(createMovieHdRezka.toDto()).isSuccess
    }
}
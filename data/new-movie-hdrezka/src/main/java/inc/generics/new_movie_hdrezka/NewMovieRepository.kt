package inc.generics.new_movie_hdrezka

import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.models.movie.SearchByName
import inc.generics.duet_api.models.movie.StatusSearch
import inc.generics.new_movie_hdrezka.models.CreateMovieHdRezka
import inc.generics.new_movie_hdrezka.models.ItemSearch

class NewMovieRepository(private val api: DuetApi) {
    suspend fun createByLink(createMovieHdRezka: CreateMovieHdRezka): Boolean {
        return api.crateMovie(createMovieHdRezka.toDto()).isSuccess
    }

    suspend fun searchMovie(name: String): List<ItemSearch>? {
        api.searchMovieByHdRezka(SearchByName(name)).onSuccess {
            return if (it.getStatus() == StatusSearch.OK) {
                it.values.map { elem ->  elem.toUi() }
            } else
                null
        }
        return null
    }
}
package inc.generics.new_movie_hdrezka

import inc.generics.duet_api.models.movie.CreateMovieByLinkDto
import inc.generics.duet_api.models.movie.SearchedMovie
import inc.generics.duet_api.models.movie.SearchedMovieList
import inc.generics.new_movie_hdrezka.models.CreateMovieHdRezka
import inc.generics.new_movie_hdrezka.models.ItemSearch
import inc.generics.new_movie_hdrezka.models.TypeMovie

fun CreateMovieHdRezka.toDto(): CreateMovieByLinkDto = CreateMovieByLinkDto(
    link = this.link,
    name = this.name,
    addName = this.addName,
    type = this.type
)

fun SearchedMovie.toUi(): ItemSearch = ItemSearch(
    name = this.name,
    addName = this.addName,
    url = this.url,
    type = type,
    rating = this.rating
)
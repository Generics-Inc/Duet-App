package inc.generics.new_movie_hdrezka

import inc.generics.duet_api.models.movie.CreateMovieByLinkDto
import inc.generics.new_movie_hdrezka.models.CreateMovieHdRezka

fun CreateMovieHdRezka.toDto(): CreateMovieByLinkDto = CreateMovieByLinkDto(
    link = this.link,
    name = this.name,
    addName = this.addName,
    type = this.type
)
package inc.generics.movie_in_detail_data

import inc.generics.duet_api.models.movie.CreateMovieByLinkDto
import inc.generics.movie_in_detail_data.models.CreateMovieHdRezka

fun CreateMovieHdRezka.toDto(): CreateMovieByLinkDto = CreateMovieByLinkDto(
    link = this.link,
    name = this.name,
    addName = this.addName,
    type = this.type
)
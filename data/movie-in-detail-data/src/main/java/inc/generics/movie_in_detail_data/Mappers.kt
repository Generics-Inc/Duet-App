package inc.generics.movie_in_detail_data

import inc.generics.duet_api.models.movie.CreateMovieByLinkDto
import inc.generics.duet_api.models.movie.MovieInDetailDto
import inc.generics.movie_in_detail_data.models.CreateMovieHdRezka
import inc.generics.movie_in_detail_data.models.MovieInDetail
import inc.generics.movie_in_detail_data.models.PartsOfMovie

fun CreateMovieHdRezka.toDto(): CreateMovieByLinkDto = CreateMovieByLinkDto(
    link = this.link,
    name = this.name,
    addName = this.addName,
    type = this.type
)

fun MovieInDetailDto.toUi() = MovieInDetail(
    id = this.id,
    photoUrl = this.movie?.photo,
    name = this.movie?.name ?: "",
    originalName = this.movie?.originalName ?: "",
    genres = this.movie?.genres ?: emptyList(),
    isWatch = this.isWatched,
    description = "",
    parts = this.movie?.partsList?.parts?.map {
        PartsOfMovie(
            link = it.link,
            name = it.name,
            type = it.type,
            rating = it.rating,
            releaseYear = it.releaseYear
        )
    } ?: emptyList()
)
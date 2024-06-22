package inc.generics.movie_data

import inc.generics.duet_api.models.movie.CreateMovieByLinkDto
import inc.generics.duet_api.models.movie.MovieDto
import inc.generics.movie_data.models.CreateMovieHdRezka
import inc.generics.movie_data.models.CreatedMovie
import inc.generics.movie_data.models.Creator
import inc.generics.movie_data.models.Movie
import inc.generics.movie_data.models.TaskCreate

fun MovieDto.toUi(): Movie = Movie(
    id = this.id,
    createdMovie = this.movie?.let { movie ->
        CreatedMovie(
            name = movie.name,
            isWatched = this.isWatched,
            photoUrl = movie.photo
        )
    },
    creator = Creator(
        name = this.creator.firstName,
        photoUrl = this.creator.photo
    ),
    taskCreate = this.taskCreate?.let { task ->
        TaskCreate(
            id = task.id,
            name = task.name,
            isError = task.isError
        )
    }
)

fun CreateMovieHdRezka.toDto(): CreateMovieByLinkDto = CreateMovieByLinkDto(
    link = this.link,
    name = this.name,
    addName = this.addName,
    type = this.type
)
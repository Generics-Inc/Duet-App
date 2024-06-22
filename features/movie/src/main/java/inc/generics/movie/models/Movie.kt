package inc.generics.movie.models

data class Movie(
    val createdMovie: CreatedMovie?,
    val creator: Creator,
    val taskCreate: TaskCreate?,
)

data class CreatedMovie(
    val name: String,
    val description: String,
    val isWatched: Boolean,
    val photoUrl: String?
)

data class TaskCreate(
    val name: String,
    val isError: Boolean
)

data class Creator(
    val name: String,
    val photoUrl: String?
)

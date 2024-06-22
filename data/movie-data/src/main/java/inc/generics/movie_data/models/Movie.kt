package inc.generics.movie_data.models

data class Movie(
    val id: Long,
    val createdMovie: CreatedMovie?,
    val creator: Creator,
    val taskCreate: TaskCreate?,
)

data class CreatedMovie(
    val name: String,
    val isWatched: Boolean,
    val photoUrl: String?
)

data class TaskCreate(
    val id: Long,
    val name: String,
    val isError: Boolean
)

data class Creator(
    val name: String,
    val photoUrl: String?
)

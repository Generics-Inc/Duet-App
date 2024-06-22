package inc.generics.duet_api.models.movie

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MovieDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("groupId")
    val groupId: Long,
    @SerializedName("isWatched")
    val isWatched: Boolean,
    @SerializedName("moreToWatch")
    val moreToWatch: List<Int>,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date,
    @SerializedName("creatorId")
    val creatorId: Long,
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("taskCreate")
    val taskCreate: TaskCreateDto?,
    @SerializedName("movie")
    val movie: MovieInfDto?,
    @SerializedName("creator")
    val creator: CreatorDto
)

data class CreatorDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("createdAt")
    val createdAt: Date
)

data class TaskCreateDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("groupMovieId")
    val groupMovieId: Long,
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("addName")
    val addName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("isError")
    val isError: Boolean,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date
) {
    fun getType(): MovieType? = try {
        MovieType.valueOf(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}

data class MovieInfDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("updatedAt")
    val updatedAt: Date,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("creatorId")
    val creatorId: Long,
    @SerializedName("originalName")
    val originalName: String
)


data class CreateMovieByLinkDto(
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("addName")
    val addName: String,
    @SerializedName("type")
    val type: String
)


enum class MovieType(val value: String) {
    FILM("FILM"), SERIAL("SERIAL"), ANIME("ANIME"), CARTOON("CARTOON")
}
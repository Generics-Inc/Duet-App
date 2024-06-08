package inc.generics.duet_api.models.movie

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MovieDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("groupId")
    val groupId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    private val type: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("isWatched")
    val isWatched: Boolean,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("profileId")
    val profileId: Long,
    @SerializedName("description")
    val description: String?,
    @SerializedName("createAt")
    val createAt: Date
) {
    fun getType(): MovieType? = try {
        MovieType.valueOf(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}

data class CreateMovieDto internal constructor(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("tags")
    val tags: List<TagDto>,
    @SerializedName("seasons")
    val seasons: List<SeasonDto>
)
fun createMovieDto(
    name: String,
    type: MovieType,
    photo: String?,
    description: String?,
): CreateMovieDto = CreateMovieDto(
    name = name,
    type = type.value,
    photo = photo,
    description = description,
    tags = emptyList(), /*todo пока хардкодим пусктой список, исправить*/
    seasons = emptyList()
)

enum class MovieType(val value: String) {
    FILM("FILM"), SERIAL("SERIAL"), ANIME("ANIME"), CARTOON("CARTOON")
}
package inc.generics.duet_api.models.movie

import com.google.gson.annotations.SerializedName

data class SearchedMovieList(
    @SerializedName("status")
    val status: String,
    @SerializedName("values")
    val values: List<SearchedMovie>
) {
    fun getStatus(): StatusSearch? {
        return try {
            StatusSearch.valueOf(status)
        } catch (e: Exception) {
            null
        }
    }
}

enum class StatusSearch(value: String) {
    OK("OK"), ERROR("ERROR"), NO_MIRROR("NO_MIRROR")
}

data class SearchByName(
    @SerializedName("text")
    val text: String
)

data class SearchedMovie(
    @SerializedName("name")
    val name: String,
    @SerializedName("addName")
    val addName: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("rating")
    val rating: Double
)

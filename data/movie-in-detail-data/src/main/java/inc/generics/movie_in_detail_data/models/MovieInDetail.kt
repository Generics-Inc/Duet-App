package inc.generics.movie_in_detail_data.models

data class MovieInDetail(
    val id: Long,
    val name: String,
    val originalName: String,
    val genres: List<String>,
    val isWatch: Boolean,
    val description: String,
    val parts: List<PartsOfMovie>
)

data class PartsOfMovie(
    val link: String,
    val name: String,
    val type: String,
    val rating: Double,
    val releaseYear: Int
) {
    fun getType(): TypeMovie? {
        return try {
            TypeMovie.valueOf(type)
        } catch (e: Exception) {
            null
        }
    }
}


enum class TypeMovie(val value: String) {
    FILM("FILM"), SERIAL("SERIAL"), ANIME("ANIME"), CARTOON("CARTOON")
}
package inc.generics.new_movie_hdrezka.models

data class ItemSearch(
    val name: String,
    val addName: String,
    val url: String,
    val type: String,
    val rating: Int
) {
    fun getType(): TypeMovie? {
        return try {
            TypeMovie.valueOf(type)
        } catch (e: Exception) {
            null
        }
    }
}


enum class TypeMovie(value: String) {
    FILM("FILM"), SERIAL("SERIAL"), ANIME("ANIME"), CARTOON("CARTOON")
}
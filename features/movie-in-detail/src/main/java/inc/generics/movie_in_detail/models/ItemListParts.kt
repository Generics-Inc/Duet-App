package inc.generics.movie_in_detail.models

data class ItemListParts(
    val name: String,
    val addName: String,
    val url: String,
    val type: String,
    val rating: Double
) {
    fun getType(): TypeMoviePart? {
        return try {
            TypeMoviePart.valueOf(type)
        } catch (e: Exception) {
            null
        }
    }
}


enum class TypeMoviePart(val value: String) {
    FILM("FILM"), SERIAL("SERIAL"), ANIME("ANIME"), CARTOON("CARTOON")
}
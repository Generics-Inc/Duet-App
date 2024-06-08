package inc.generics.duet_api.models.movie

import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("color")
    val color: String
)
package inc.generics.duet_api.models.groups

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Group(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("mainProfileId")
    val mainProfileId: Long,
    @SerializedName("secondProfileId")
    val secondProfileId: Long,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("inviteCode")
    val inviteCode: String,
    @SerializedName("relationStartedAt")
    val relationStartedAt: Date,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAtL")
    val updatedAtL: Date
)

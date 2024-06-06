package inc.generics.duet_api.models.profile

import com.google.gson.annotations.SerializedName

data class PartnerDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("groupId")
    val groupId: Long,
    @SerializedName("vkId")
    val vkId: Long,
    @SerializedName("gender")
    private val gender: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("createdAt")
    private val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)

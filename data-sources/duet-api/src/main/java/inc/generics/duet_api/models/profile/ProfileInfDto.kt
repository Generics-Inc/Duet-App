package inc.generics.duet_api.models.profile

import com.google.gson.annotations.SerializedName
import inc.generics.duet_api.models.groups.Group
import inc.generics.duet_api.util.stringToLocalDateTime
import java.time.LocalDateTime

data class ProfileInfDto(
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
    val updatedAt: String,
    @SerializedName("group")
    val group: Group?,
    @SerializedName("partner")
    val partner: PartnerDto?,
    @SerializedName("groupStatus")
    val groupStatus: StatusUsersGroupDto
) {
    fun getGender(): Gender? = try {
        Gender.valueOf(gender)
    } catch (e: IllegalArgumentException) {
        null
    }

    fun getCreateAt(): LocalDateTime? = stringToLocalDateTime(createdAt)
    fun getUpdatedAt(): LocalDateTime? = stringToLocalDateTime(updatedAt)
}

enum class Gender(val value: String) {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER"),
    NOT_SPECIFIED("NOT_SPECIFIED")
}


package inc.generics.duet_api.models.profile

import com.google.gson.annotations.SerializedName
import inc.generics.duet_api.models.groups.GroupLite
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
    val groupId: Long?,
    @SerializedName("gender")
    private val gender: String,
    @SerializedName("description")
    val status: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("createdAt")
    private val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("group")
    val group: GroupLite?,
    @SerializedName("partner")
    val partner: PartnerDto?,
    @SerializedName("groupStatus")
    val groupStatus: StatusUsersGroupDto,
    @SerializedName("accounts")
    val accounts: List<Account>
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

enum class AccountType(val value: String) {
    EMAIL("EMAIL"), VK("VK"), TG("TG")
}

data class Account(
    @SerializedName("id")
    val id: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("UUID")
    val uuid: String,
    @SerializedName("type")
    private val type: String
) {
    fun getType(): AccountType? = try {
        AccountType.valueOf(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}
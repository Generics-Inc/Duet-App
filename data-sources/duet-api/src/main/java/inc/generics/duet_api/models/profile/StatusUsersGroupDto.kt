package inc.generics.duet_api.models.profile

import com.google.gson.annotations.SerializedName

data class StatusUsersGroupDto(
    @SerializedName("self")
    private val self: String,
    @SerializedName("partner")
    private val partner: String
) {
    fun getSelfStatus(): UserStatusInGroup? = getStatusWithTry(self)
    fun getPartnerStatus(): UserStatusInGroup? = getStatusWithTry(partner)
}

enum class UserStatusInGroup(val value: String) {
    NOT_IN_GROUP("NOT_IN_GROUP"),
    NOT_IN_GROUP_WITH_ARCHIVE("NOT_IN_GROUP_WITH_ARCHIVE"),
    IN_GROUP("IN_GROUP")
}

internal fun StatusUsersGroupDto.getStatusWithTry(value: String) =
    try {
        UserStatusInGroup.valueOf(value)
    } catch (e: IllegalArgumentException) {
        null
    }
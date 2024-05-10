package inc.generics.duet_api.models.profile

import com.google.gson.annotations.SerializedName

data class StatusUsersGroupDto(
    @SerializedName("self")
    private val self: String,
    @SerializedName("partner")
    private val partner: String
) {
    fun getSelfStatus(): UserStatusInGroup? = getUserStatusWithTry(self)
    fun getPartnerStatus(): PartnerStatusInGroup? = getPartnerStatusWithTry(partner)
}

enum class UserStatusInGroup(val value: String) {
    NOT_IN_GROUP("NOT_IN_GROUP"),
    NOT_IN_GROUP_WITH_ARCHIVE("NOT_IN_GROUP_WITH_ARCHIVE"),
    IN_GROUP("IN_GROUP")
}

enum class PartnerStatusInGroup(val value: String) {
    NOT_IN_GROUP("NOT_IN_GROUP"),
    NOT_IN_GROUP_WITH_ARCHIVE("NOT_IN_GROUP_WITH_ARCHIVE"),
    IN_GROUP("IN_GROUP"),
    LEAVED("LEAVED")
}

internal fun getUserStatusWithTry(value: String): UserStatusInGroup? =
    try {
        UserStatusInGroup.valueOf(value)
    } catch (e: IllegalArgumentException) {
        null
    }

internal fun getPartnerStatusWithTry(value: String): PartnerStatusInGroup? =
    try {
        PartnerStatusInGroup.valueOf(value)
    } catch (e: IllegalArgumentException) {
        null
    }
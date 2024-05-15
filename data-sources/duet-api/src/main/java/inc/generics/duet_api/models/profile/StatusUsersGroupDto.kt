package inc.generics.duet_api.models.profile

import com.google.gson.annotations.SerializedName

data class StatusUsersGroupDto(
    @SerializedName("selfId")
    val selfId: Long,
    @SerializedName("partnerId")
    val partnerId: Long,
    @SerializedName("selfStatus")
    private val self: String,
    @SerializedName("partnerStatus")
    private val partner: String,
    @SerializedName("isMainInGroup")
    val isMainInGroup: Boolean
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
    NO_PARTNER("NO_PARTNER"),
    GROUP_IN_ARCHIVE("GROUP_IN_ARCHIVE"),
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
package inc.generics.profile_data.models

data class ProfileInfo(
    val fullName: String,
    val status: String?,
    val userPhotoUrl: String?,
    val groupInfo: GroupInfo?,
    val accountsList: List<UserConnectedAccount> = emptyList()
)

data class GroupInfo(
    val nameGroup: String,
    val namePartner: String?,
    val groupPhotoUrl: String?,
    val partnerPhotoUrl: String?,
    val isPartnerInGroup: Boolean
)

data class UserConnectedAccount(
    val username: String,
    val typeAccount: TypeAccount
)

enum class TypeAccount {
    VK, TG, NONE, EMAIL
}

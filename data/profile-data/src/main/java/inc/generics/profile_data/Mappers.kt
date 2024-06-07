package inc.generics.profile_data

import inc.generics.duet_api.models.profile.AccountType
import inc.generics.duet_api.models.profile.ProfileInfDto
import inc.generics.profile_data.models.GroupInfo
import inc.generics.profile_data.models.ProfileInfo
import inc.generics.profile_data.models.TypeAccount
import inc.generics.profile_data.models.UserConnectedAccount

internal fun ProfileInfDto.toProfileInfoUi(): ProfileInfo =
    ProfileInfo(fullName = this.lastName + " " + this.firstName,
        status = this.status,
        userPhotoUrl = this.photo,
        groupInfo = if (this.group != null) GroupInfo(
            nameGroup = this.group!!.name,
            namePartner = this.partner?.let {
                it.firstName + " " + it.lastName
            },
            groupPhotoUrl = this.group!!.photo,
            partnerPhotoUrl = this.partner?.photo,
            isPartnerInGroup = false
        ) else null,
        accountsList = this.accounts.map {
            UserConnectedAccount(
                username = this.username, typeAccount = it.getType().toTypeAccountUI()
            )
        }
    )

internal fun AccountType?.toTypeAccountUI():TypeAccount = when(this) {
    AccountType.EMAIL -> TypeAccount.EMAIL
    AccountType.VK -> TypeAccount.VK
    AccountType.TG -> TypeAccount.TG
    else -> TypeAccount.NONE
}
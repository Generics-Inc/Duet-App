package inc.generics.group_without_partner

import inc.generics.duet_api.api.DuetApi
import inc.generics.group_without_partner.models.GroupWithoutPartner
import inc.generics.group_without_partner.models.InviteCode

class GroupWithoutPartnerRepository(private val api: DuetApi) {
    suspend fun getGroup(): GroupWithoutPartner? {
        return api.getUserGroup().getOrNull()?.toGroupWithoutPartner()
    }

    suspend fun newInviteCode(): InviteCode? {
        return api.generateNewInviteCode().getOrNull()?.toInviteCode()
    }

    suspend fun leaveGroup(): Boolean {
        try {
            api.leaveGroup()
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
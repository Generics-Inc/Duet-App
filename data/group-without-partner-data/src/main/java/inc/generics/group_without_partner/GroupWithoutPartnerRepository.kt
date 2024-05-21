package inc.generics.group_without_partner

import inc.generics.duet_api.api.DuetApi
import inc.generics.group_without_partner.models.GroupWithoutPartner
import inc.generics.utils_data.exceptions.safeRequest

class GroupWithoutPartnerRepository(private val api: DuetApi) {
    suspend fun getGroup(): GroupWithoutPartner? {
        return api.getUserGroup().getOrNull()?.toGroupWithoutPartner()
    }

    suspend fun leaveGroup(): Boolean {
        return safeRequest {
            api.leaveGroup()
        }
    }
}
package inc.generics.group_left_by_partner_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.utils_data.exceptions.safeRequest

class GroupLeftByPartnerRepository(
    private val api: DuetApi
) {
    suspend fun leaveGroup(): Boolean = safeRequest {
        api.leaveGroup()
    }

    suspend fun deletePartner(): Boolean = safeRequest {
        api.deletePartnerInGroup()
    }
}
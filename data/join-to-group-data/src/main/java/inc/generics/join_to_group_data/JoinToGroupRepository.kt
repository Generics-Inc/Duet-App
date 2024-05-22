package inc.generics.join_to_group_data

import inc.generics.duet_api.api.DuetApi

class JoinToGroupRepository(private val api: DuetApi) {
    suspend fun joinToGroupByInviteCode(inviteCode: String): Boolean {
        return api.joinToGroup(inviteCode).isFailure
    }
}
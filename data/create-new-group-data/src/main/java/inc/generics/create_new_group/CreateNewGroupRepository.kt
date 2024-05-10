package inc.generics.create_new_group

import inc.generics.create_new_group.models.Group
import inc.generics.duet_api.api.DuetApi

class CreateNewGroupRepository(val api: DuetApi) {
    suspend fun create(group: Group) {}
}
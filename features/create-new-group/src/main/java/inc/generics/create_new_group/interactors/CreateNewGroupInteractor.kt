package inc.generics.create_new_group.interactors

import inc.generics.create_new_group.models.GroupSimple

interface CreateNewGroupInteractor {
    suspend fun create(newGroup: GroupSimple)
}
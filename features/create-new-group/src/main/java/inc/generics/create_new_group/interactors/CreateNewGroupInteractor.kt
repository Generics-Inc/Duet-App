package inc.generics.create_new_group.interactors

import inc.generics.domain_models.models.GroupSimple

interface CreateNewGroupInteractor {
    suspend fun create(newGroup: GroupSimple)
}
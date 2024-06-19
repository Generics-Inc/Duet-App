package inc.generics.group_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.group_data.models.Group
import inc.generics.utils_data.exceptions.safeRequest

class GroupRepository(
    private val api: DuetApi
) {
    suspend fun getCurrentGroup(): Group? {
        api.getUserGroup().onSuccess {
            return it.toUiModel()
        }
        return null
    }

    suspend fun deleteGroup(): Boolean = safeRequest {
        api.leaveGroup()
    }
}
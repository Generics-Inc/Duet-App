package inc.generics.archive_data

import inc.generics.archive_data.models.ArchiveItem
import inc.generics.duet_api.api.DuetApi
import inc.generics.utils_data.exceptions.safeRequest

class ArchiveRepository(private val api: DuetApi) {
    suspend fun allArchive(): List<ArchiveItem>? {
        api.allArchives().onSuccess { archives ->
            return archives.map {
                it.mapToUi()
            }
        }
        return null
    }

    suspend fun revert(id: Long): Boolean {
        return api.revertGroup(id).isSuccess
    }

    suspend fun delete(id: Long): Boolean {
        return safeRequest {
            api.deleteGroup(id)
        }
    }
}
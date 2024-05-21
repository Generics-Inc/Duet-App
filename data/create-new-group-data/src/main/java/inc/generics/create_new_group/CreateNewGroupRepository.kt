package inc.generics.create_new_group

import android.content.Context
import inc.generics.create_new_group.models.Group
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.util.sendRequestToCreateGroup
import inc.generics.utils_data.img.getRealPathFromURI
import java.io.File
import java.util.Date

class CreateNewGroupRepository(private val api: DuetApi, private val context: Context) {
    suspend fun create(group: Group): Boolean {
        val result = sendRequestToCreateGroup(
            api = api,
            name = group.name,
            description = "this group is so cute!",
            relationStartedAt = Date(),
            file = if (group.fileUri?.path.isNullOrEmpty())
                null
            else
                File(getRealPathFromURI(group.fileUri!!, context))
        )

        return result.isSuccess
    }
}
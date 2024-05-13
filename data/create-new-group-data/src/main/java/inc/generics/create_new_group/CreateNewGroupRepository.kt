package inc.generics.create_new_group

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import inc.generics.create_new_group.models.Group
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.util.sendRequestToCreateGroup
import java.io.File
import java.util.Date

class CreateNewGroupRepository(private val api: DuetApi, private val context: Context) {
    suspend fun create(group: Group): Boolean {
        val result = sendRequestToCreateGroup(
            api = api,
            name = group.name,
            description = "this group is so cute!",
            relationStartedAt = Date(),
            file = if (group.fileUri?.path.isNullOrEmpty()) null else File(getRealPathFromURI(group.fileUri!!))
        )

        return result.isSuccess
    }
    private fun getRealPathFromURI(uri: Uri): String {
        val result: String
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        if (cursor == null) {
            result = uri.path ?: ""
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }
}
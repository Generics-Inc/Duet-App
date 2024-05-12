package inc.generics.create_new_group

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import inc.generics.create_new_group.models.Group
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.util.sendRequestToCreateGroup
import java.io.File
import java.io.FileOutputStream
import java.util.Date

class CreateNewGroupRepository(private val api: DuetApi, private val context: Context) {
    suspend fun create(group: Group): Boolean {
        val file = getPathFromUri(context, group.fileUri!!)

        Log.d("File", file.toString())
        Log.d("File", file.isFile.toString())
        Log.d("File", file.absoluteFile.canRead().toString())
        Log.d("File", file.absoluteFile.toString())
        val result = sendRequestToCreateGroup(
            api = api,
            name = group.name,
            description = "this group is so cute!",
            relationStartedAt = Date(),
            file = if (group.fileUri?.path.isNullOrEmpty()) null else file
        )

        return result.isSuccess
    }
    private fun getPathFromUri(context: Context, uri: Uri): File {
        var filePath = ""
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = it.getString(columnIndex)
            it.close()
        }

        val file = File(filePath)

        val bitmap = BitmapFactory.decodeFile(filePath)
        val fos = FileOutputStream(file)
        bitmap?.compress(Bitmap.CompressFormat.PNG, 80, fos)

        fos.close()

        return file
    }
}
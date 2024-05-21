package inc.generics.utils_data.img

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

fun getRealPathFromURI(uri: Uri, context: Context): String {
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
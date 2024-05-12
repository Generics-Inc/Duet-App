package inc.generics.duet_api.util

import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.models.groups.Group
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.Date

suspend fun sendRequestToCreateGroup(
    api: DuetApi,
    name: String,
    description: String,
    relationStartedAt: Date,
    file: File?,
): Result<Group> {
    val result = api.createGroup(
        name = name,
        description = description,
        relationStartedAt = relationStartedAt,
        file = if(file != null) fileToMultipartBodyPart(file) else null
    )
    return result
}

internal fun fileToMultipartBodyPart(file: File): MultipartBody.Part {
    val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("file", file.name, requestFile)
}
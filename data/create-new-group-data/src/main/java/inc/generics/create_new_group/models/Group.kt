package inc.generics.create_new_group.models

import android.net.Uri

data class Group(
    val name: String,
    val fileUri: Uri?
)

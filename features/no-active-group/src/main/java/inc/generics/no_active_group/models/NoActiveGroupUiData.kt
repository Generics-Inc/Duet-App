package inc.generics.no_active_group.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoActiveGroupUiData(
    val userHaveArchive: Boolean
): Parcelable

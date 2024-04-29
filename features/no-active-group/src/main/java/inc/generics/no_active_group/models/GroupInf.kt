package inc.generics.no_active_group.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupInf(
    val userDeletedOrLeaveGroup: StatusGroup,
    val partnerStatusGroup: StatusGroup?
): Parcelable

enum class StatusGroup(val value: String) {
    LEAVE("leave"),
    DELETED("deleted"),
    ACTIVE("active"),
}
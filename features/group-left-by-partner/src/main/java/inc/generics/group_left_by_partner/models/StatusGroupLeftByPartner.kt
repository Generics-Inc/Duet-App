package inc.generics.group_left_by_partner.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatusGroupLeftByPartner(
    val isMainInGroup: Boolean,
    val isPartnerDeleteGroup: Boolean
) : Parcelable

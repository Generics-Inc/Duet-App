package inc.generics.duet_api.models.groups

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RequestToGroup(
    @SerializedName("id")
    val id: Long,
    @SerializedName("profileId")
    val profileId: Long,
    @SerializedName("groupId")
    val groupId: Long,
    @SerializedName("inviteCode")
    val inviteCode: String?,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("photo")
    val photoUrl: String?
)

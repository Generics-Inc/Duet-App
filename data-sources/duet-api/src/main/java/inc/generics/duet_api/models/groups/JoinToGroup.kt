package inc.generics.duet_api.models.groups

import com.google.gson.annotations.SerializedName

data class JoinToGroup(
    @SerializedName("id")
    val id: Long,
    @SerializedName("profileId")
    val profileId: Long,
    @SerializedName("groupId")
    val groupId: Long,
    @SerializedName("inviteCode")
    val inviteCode: String,
    @SerializedName("createAt")
    val createAt: String
)

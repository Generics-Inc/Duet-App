package inc.generics.duet_api.models.groups

import com.google.gson.annotations.SerializedName
import inc.generics.duet_api.models.profile.Gender
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
    @SerializedName("profile")
    val profile: ProfileInRequest
)

data class ProfileInRequest(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    private val gender: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("createdAt")
    val createdAt: String
) {
    fun getGender(): Gender? = try {
        Gender.valueOf(gender)
    } catch (e: IllegalArgumentException) {
        null
    }
}

/*
{
    "id": 0,
    "profileId": 0,
    "groupId": 0,
    "inviteCode": "string",
    "createdAt": "2024-06-07T08:54:31.392Z",
    "profile": {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "gender": "MALE",
      "description": "Казак с плеч",
      "photo": "string",
      "createdAt": "2024-06-07T08:54:31.392Z"
    },
    "group": {
      "id": 0,
      "name": "string",
      "description": "string",
      "photo": "string",
      "createdAt": "2024-06-07T08:54:31.392Z"
    }
  }
* */
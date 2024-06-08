package inc.generics.duet_api.models.archives

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArchivesItemDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("profileId")
    val profileId: Long,
    @SerializedName("createAt")
    val createAt: Date,
    @SerializedName("profile")
    val profile: ProfileInArchive,
    @SerializedName("group")
    val group: Group
)

data class ProfileInArchive(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("createAt")
    val createAt: Date
)

data class Group(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("createdAt")
    val createdAt: Date
)

/*
{
    "id": 0,
    "groupId": 0,
    "profileId": 0,
    "createdAt": "2024-06-08T10:37:32.431Z",
    "profile": {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "gender": "MALE",     // не беру инфу по полу (она не нужна)
      "description": "Казак с плеч",
      "photo": "string",
      "createdAt": "2024-06-08T10:37:32.432Z"
    },
    "group": {
      "id": 0,
      "name": "string",
      "description": "string",
      "photo": "string",
      "createdAt": "2024-06-08T10:37:32.432Z"
    }
  }
* */

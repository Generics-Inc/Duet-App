package inc.generics.duet_api.models.archives

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArchivesItemDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("profileId")
    val profileId: Long?,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("partner")
    val partner: Partner?,
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
    @SerializedName("createdAt")
    val createdAt: Date
)

data class Partner(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("createdAt")
    val createdAt: Date
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
    "partnerId": 0,
    "createdAt": "2024-06-12T16:10:44.384Z",
    "profile": {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "gender": "MALE",
      "description": "Конец близок",
      "photo": "string",
      "createdAt": "2024-06-12T16:10:44.384Z"
    },
    "partner": {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "gender": "MALE",
      "description": "Конец близок",
      "photo": "string",
      "createdAt": "2024-06-12T16:10:44.384Z"
    },
    "group": {
      "id": 0,
      "name": "string",
      "description": "string",
      "photo": "string",
      "createdAt": "2024-06-12T16:10:44.384Z"
    }
  }
* */

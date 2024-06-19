package inc.generics.duet_api.models.groups

import com.google.gson.annotations.SerializedName
import java.util.Date

data class GroupLite(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("mainProfileId")
    val mainProfileId: Long,
    @SerializedName("secondProfileId")
    val secondProfileId: Long,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("inviteCode")
    val inviteCode: String,
    @SerializedName("relationStartedAt")
    val relationStartedAt: Date,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAtL: Date
)

data class Group(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("mainProfileId")
    val mainProfileId: Long,
    @SerializedName("secondProfileId")
    val secondProfileId: Long?,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("inviteCode")
    val inviteCode: String,
    @SerializedName("relationStartedAt")
    val relationStartedAt: Date,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAtL: Date,
    @SerializedName("groupArchives")
    val groupArchives: List<GroupArchive>,
    @SerializedName("partner")
    val partner: Partner?

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

data class GroupArchive(
    @SerializedName("id")
    val id: Long,
    @SerializedName("groupId")
    val groupId: Long,
    @SerializedName("profileId")
    val profileId: Long,
    @SerializedName("createdAt")
    val createdAt: Date
)

// model data from server
/*
{
  "id": 0,
  "name": "string",
  "description": "string",
  "photo": "string",
  "createdAt": "2024-06-07T08:46:57.041Z",
  "mainProfileId": 0,
  "secondProfileId": 0,
  "inviteCode": "string",
  "relationStartedAt": "2024-06-07T08:46:57.041Z",
  "updatedAt": "2024-06-07T08:46:57.041Z",
  "groupArchives": [
    {
      "id": 0,
      "groupId": 0,
      "profileId": 0,
      "createdAt": "2024-06-07T08:46:57.041Z"
    }
  ],
  "groupRequests": [
    {
      "id": 0,
      "profileId": 0,
      "groupId": 0,
      "inviteCode": "string",
      "createdAt": "2024-06-07T08:46:57.041Z"
    }
  ],
  "mainProfile": {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "gender": "MALE",
    "description": "Казак с плеч",
    "photo": "string",
    "createdAt": "2024-06-07T08:46:57.041Z"
  },
  "secondProfile": {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "gender": "MALE",
    "description": "Казак с плеч",
    "photo": "string",
    "createdAt": "2024-06-07T08:46:57.041Z"
  }
}
* */

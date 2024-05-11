package inc.generics.duet_api.models.groups

import com.google.gson.annotations.SerializedName

/*
* {
  "id": 0,
  "name": "string",
  "description": "string",
  "mainProfileId": 0,
  "secondProfileId": 0,
  "photo": "string",
  "inviteCode": "string",
  "relationStartedAt": "2024-05-11T18:04:59.611Z",
  "createdAt": "2024-05-11T18:04:59.611Z",
  "updatedAt": "2024-05-11T18:04:59.611Z",
  "groupArchives": [
    {
      "id": 0,
      "groupId": 0,
      "profileId": 0
    }
  ],
  "groupRequests": [
    {
      "id": 0,
      "profileId": 0,
      "groupId": 0,
      "inviteCode": "string",
      "createdAt": "2024-05-11T18:04:59.611Z"
    }
  ],
  "mainProfile": {
    "id": 0,
    "userId": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "birthday": "string",
    "groupId": 0,
    "vkId": 284470002,
    "gender": "MALE",
    "status": "Казак с плеч",
    "photo": "string",
    "createdAt": "2024-05-11T18:04:59.611Z",
    "updatedAt": "2024-05-11T18:04:59.611Z"
  },
  "secondProfile": {
    "id": 0,
    "userId": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "birthday": "string",
    "groupId": 0,
    "vkId": 284470002,
    "gender": "MALE",
    "status": "Казак с плеч",
    "photo": "string",
    "createdAt": "2024-05-11T18:04:59.611Z",
    "updatedAt": "2024-05-11T18:04:59.611Z"
  }
}
* */
data class GroupFull(
    @SerializedName("id")
    val id: Long
)

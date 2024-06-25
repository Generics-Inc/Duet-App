package inc.generics.duet_api.models.movie

import java.util.Date

data class MovieInDetailDto(
    val id: Long,
    val groupId: Long,
    val isWatched: Boolean,
    val moreToWatch: List<Int>,
    val createdAt: Date,
    val updatedAt: Date,
    val creatorId: Long?,
    val movieId: Long?,
    val movie: MovieInDetailInf?
)

data class MovieInDetailInf(
    val id: Long,
    val name: String,
    val type: String,
    val photo: String?,
    val originalName: String?,
    val genres: List<String>,
    val partsList: PartsList?
) {
    fun getType(): MovieType? = try {
        MovieType.valueOf(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}

data class PartsList(
    val id: Long,
    val parts: List<Parts>
)

data class Parts(
    val link: String,
    val releaseYear: Int,
    val name: String,
    val type: String,
    val rating: Double
) {
    fun getType(): MovieType? = try {
        MovieType.valueOf(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}

/*
* {
  "id": 0,
  "groupId": 0,
  "isWatched": true,
  "moreToWatch": [
    0,
    0
  ],
  "createdAt": "2024-06-25T10:51:37.118Z",
  "updatedAt": "2024-06-25T10:51:37.118Z",
  "creatorId": 0,
  "movieId": 0,
  "watchedSeries": [
    {
      "seriaId": 0,
      "groupMovieId": 0,
      "createdAt": "2024-06-25T10:51:37.118Z"
    }
  ],
  "ratings": [
    {
      "id": 0,
      "profileId": 0,
      "groupMovieId": 0,
      "scope": 10,
      "createdAt": "2024-06-25T10:51:37.118Z",
      "profile": {
        "id": 0,
        "username": "string",
        "firstName": "string",
        "lastName": "string",
        "gender": "MALE",
        "description": "Конец близок",
        "photo": "string",
        "createdAt": "2024-06-25T10:51:37.118Z"
      }
    }
  ],
  "movie": {
    "id": 0,
    "name": "string",
    "type": "FILM",
    "photo": "string",
    "updatedAt": "2024-06-25T10:51:37.118Z",
    "createdAt": "2024-06-25T10:51:37.118Z",
    "creatorId": 0,
    "originalName": "string",
    "genres": [
      "string"
    ],
    "moderated": true,
    "ageRating": 0,
    "partsListId": 0,
    "time": 0,
    "link": "string",
    "country": "string",
    "slogan": "string",
    "description": "string",
    "releaseDate": "2024-06-25T10:51:37.118Z",
    "ratings": [
      {
        "id": 0,
        "movieId": 0,
        "providerName": "string",
        "countOfScopes": 0,
        "scope": 0
      }
    ],
    "seasons": [
      {
        "id": 0,
        "movieId": 0,
        "number": 0,
        "releaseDate": "2024-06-25T10:51:37.118Z",
        "series": [
          {
            "id": 0,
            "seasonId": 0,
            "number": 0,
            "name": "string",
            "releaseDate": "2024-06-25T10:51:37.118Z"
          }
        ]
      }
    ],
    "partsList": {
      "id": 0,
      "parts": [
        {
          "link": "string",
          "releaseYear": 0,
          "name": "string",
          "type": "FILM",
          "current": true,
          "rating": 0,
          "partsListId": 0,
          "movieId": 0
        }
      ]
    }
  },
  "taskCreate": {
    "id": 0,
    "groupMovieId": 0,
    "link": "string",
    "name": "string",
    "addName": "string",
    "type": "FILM",
    "isError": true,
    "createdAt": "2024-06-25T10:51:37.118Z",
    "updatedAt": "2024-06-25T10:51:37.118Z"
  }
}*/
package inc.generics.duet_api.models.movie

import com.google.gson.annotations.SerializedName

class SeasonDto(
    val name: String,
    val series: List<SeriesDto>
)

class SeriesDto(
    @SerializedName("name")
    val name: String
)

/*[ { "name": "testSeason", "series": [ { "name": "testSeria" } ] } ]*/
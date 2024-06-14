package inc.generics.group_data.models

import java.util.Date

data class Group(
    val name: String,
    val photoUrl: String?,
    val dayTogether: Int,
    val statistic: Statistic,
    val partner: Partner,
    val createdAt: Date
)

data class Statistic(
    val moviesCount: Int,
    val eventsCount: Int,
    val kitchenCount: Int
)

data class Partner(
    val name: String,
    val photoUrl: String?,
)

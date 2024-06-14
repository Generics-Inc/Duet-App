package inc.generics.group_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.group_data.models.Group
import inc.generics.group_data.models.Partner
import inc.generics.group_data.models.Statistic
import kotlinx.coroutines.delay
import java.util.Date

class GroupRepository(
    private val api: DuetApi
) {
    suspend fun getCurrentGroup(): Group {
        //todo: временная имитация запроса
        delay(1000)
        return Group(
            name = "Такая-то группа",
            photoUrl = null,
            dayTogether = 20,
            statistic = Statistic(
                moviesCount = 10,
                eventsCount = 6,
                kitchenCount = 2
            ),
            partner = Partner(
                name = "Имя партнера",
                photoUrl = null
            ),
            createdAt = Date()
        )
    }
}
package inc.generics.group_data

import inc.generics.group_data.models.Group
import inc.generics.group_data.models.Partner
import inc.generics.group_data.models.Statistic
import inc.generics.duet_api.models.groups.Group as GroupDto

fun GroupDto.toUiModel(): Group = Group(
    name = this.name,
    photoUrl = this.photo,
    dayTogether = 1,  //todo: GroupDto пока что предоставляет не все данные
    statistic = Statistic(
        moviesCount = 2,
        eventsCount = 0,
        kitchenCount = 0
    ),
    partner = this.partner?.let { partner ->
        Partner(
            name = partner.lastName + " " + partner.firstName ,
            photoUrl = partner.photo
        )
    },
    createdAt = this.createdAt
)

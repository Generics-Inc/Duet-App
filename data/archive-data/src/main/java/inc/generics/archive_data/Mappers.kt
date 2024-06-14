package inc.generics.archive_data

import inc.generics.archive_data.models.ArchiveItem
import inc.generics.archive_data.models.Partner
import inc.generics.duet_api.models.archives.ArchivesItemDto

internal fun ArchivesItemDto.mapToUi(): ArchiveItem = ArchiveItem(
    id = this.id,
    name = this.group.name,
    photoUrl = this.group.photo,
    partner = this.partner?.let { thisPartner ->
        Partner(
            name = thisPartner.firstName + " " + thisPartner.lastName,
            photoUrl = thisPartner.photo
        )
    },
    moviesCount = 0,
    eventsCount = 0,
    kitchenCount = 0,
    dayBeforeDeleted = 30,
    createGroupAt = this.group.createdAt,
    toArchiveGroupAt = this.createdAt
)
// todo: пока что данные по *Count и dayBeforeDeleted не приходят от сервера, исправить
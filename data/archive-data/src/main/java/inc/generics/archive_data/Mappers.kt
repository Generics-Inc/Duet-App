package inc.generics.archive_data

import inc.generics.archive_data.models.ArchiveItem
import inc.generics.archive_data.models.Partner
import inc.generics.duet_api.models.archives.ArchivesItemDto

internal fun ArchivesItemDto.mapToUi(): ArchiveItem = ArchiveItem(
    id = this.id,
    name = this.profile.firstName + " " + this.profile.lastName,
    photoUrl = this.group.photo,
    partner = Partner(
        name = "Empty now",
        isPartnerInGroup = true,
        photoUrl = null
    ),
    moviesCount = 0,
    eventsCount = 0,
    kitchenCount = 0,
    dayBeforeDeleted = 30,
    createGroupAt = this.group.createdAt,
    toArchiveGroupAt = this.createAt
)
// todo: пока что данные по партнеру и по *Count не приходят от сервера, исправить
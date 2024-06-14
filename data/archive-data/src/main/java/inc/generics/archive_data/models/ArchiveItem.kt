package inc.generics.archive_data.models

import java.util.Date

data class ArchiveItem(
    val id: Long,
    val name: String,
    val photoUrl: String?,
    val partner: Partner?,
    val moviesCount: Int,
    val eventsCount: Int,
    val kitchenCount: Int,
    val dayBeforeDeleted: Int,
    val createGroupAt: Date,
    val toArchiveGroupAt: Date
)

data class Partner(
    val name: String,
    val photoUrl: String?
)

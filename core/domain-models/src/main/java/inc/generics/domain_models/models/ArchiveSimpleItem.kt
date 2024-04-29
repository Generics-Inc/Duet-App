package inc.generics.domain_models.models

data class ArchiveSimpleItem(
    val id: Long,
    val name: String,
    val imgUrl: String?,
    val timerToDelete: String
)

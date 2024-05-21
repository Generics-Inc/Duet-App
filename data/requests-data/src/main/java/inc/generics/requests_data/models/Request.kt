package inc.generics.requests_data.models

data class Request(
    val id: Long,
    val profileId: Long,
    val firstName: String?,
    val lastName: String?,
    val photoUrl: String?
)

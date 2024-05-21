package inc.generics.requests_data

import inc.generics.duet_api.models.groups.RequestToGroup
import inc.generics.requests_data.models.Request

fun RequestToGroup.toRequest(): Request = Request(
    id = this.id,
    profileId = this.profileId,
    firstName = this.firstName,
    lastName = this.lastName,
    photoUrl = this.photoUrl
)
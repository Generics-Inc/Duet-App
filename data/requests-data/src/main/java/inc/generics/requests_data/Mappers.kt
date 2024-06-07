package inc.generics.requests_data

import inc.generics.duet_api.models.groups.RequestToGroup
import inc.generics.requests_data.models.Request

internal fun RequestToGroup.toRequest(): Request = Request(
    id = this.id,
    profileId = this.profileId,
    firstName = this.profile.firstName,
    lastName = this.profile.lastName,
    photoUrl = this.profile.photo
)
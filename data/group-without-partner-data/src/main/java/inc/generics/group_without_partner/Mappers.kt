package inc.generics.group_without_partner

import inc.generics.duet_api.models.groups.Group
import inc.generics.group_without_partner.models.GroupWithoutPartner

internal fun Group.toGroupWithoutPartner(): GroupWithoutPartner {
    return GroupWithoutPartner(
        name = this.name,
        inviteCode = this.inviteCode,
        photoUrl = this.photo
    )
}
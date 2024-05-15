package inc.generics.group_without_partner

import inc.generics.duet_api.models.groups.Group
import inc.generics.group_without_partner.models.GroupWithoutPartner
import inc.generics.group_without_partner.models.InviteCode

internal fun Group.toGroupWithoutPartner(): GroupWithoutPartner {
    return GroupWithoutPartner(
        name = this.name,
        inviteCode = this.inviteCode
    )
}

internal fun Group.toInviteCode(): InviteCode {
    return InviteCode(
        code = this.inviteCode
    )
}
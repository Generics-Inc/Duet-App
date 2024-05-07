package inc.generics.authorization_data

import inc.generics.authorization_data.util.DeviceInf
import inc.generics.duet_api.models.auth.DeviceInfDto

fun DeviceInf.toDto(): DeviceInfDto = DeviceInfDto(
    uuid = uuid,
    name = name,
    os = os
)
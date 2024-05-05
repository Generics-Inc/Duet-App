package inc.generics.duet_api.models.auth

import com.google.gson.annotations.SerializedName

data class DeviceInfDto(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("os")
    val os: String
)
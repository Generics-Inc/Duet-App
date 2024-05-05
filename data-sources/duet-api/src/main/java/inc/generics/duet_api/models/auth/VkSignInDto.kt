package inc.generics.duet_api.models.auth

import com.google.gson.annotations.SerializedName

data class VkSignInDto(
    @SerializedName("vk")
    val vk: VkDto,
    @SerializedName("device")
    val device: DeviceInfDto
)

data class VkDto(
    @SerializedName("token")
    val token: String
)
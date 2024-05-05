package inc.generics.duet_api.models.auth

import com.google.gson.annotations.SerializedName

data class SignInDto(
    @SerializedName("user")
    val user: User,
    @SerializedName("device")
    val device: DeviceInfDto
)

data class User(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
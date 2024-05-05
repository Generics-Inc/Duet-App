package inc.generics.duet_api.models.auth

import com.google.gson.annotations.SerializedName

data class RefreshTokenDto(
    @SerializedName("accessToken")
    val accessToken: String
)

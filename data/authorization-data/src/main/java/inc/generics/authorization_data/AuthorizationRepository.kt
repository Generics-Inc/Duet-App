package inc.generics.authorization_data

import inc.generics.authorization_data.util.DeviceInfProvider
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.models.auth.VkDto
import inc.generics.duet_api.models.auth.VkSignInDto
import inc.generics.duet_local.sp.SPHelper

class AuthorizationRepository(
    private val api: DuetApi,
    private val localStore: SPHelper,
    private val deviceInfProvider: DeviceInfProvider
) {
    suspend fun authViaVk(accessTokeVk: String): Boolean {
        val deviceInf = deviceInfProvider.getInformation()

        val result = api.vkSignIn(VkSignInDto(
            vk = VkDto(accessTokeVk),
            device = deviceInf.toDto()
        ))

        result.onSuccess {
            saveTokens(it.accessToken, it.refreshToken)
        }

        return result.isSuccess
    }

    private fun saveTokens(newAccessToken: String, newRefreshToken: String) = with(localStore) {
        accessToken = newAccessToken
        refreshToken = newRefreshToken
    }
}
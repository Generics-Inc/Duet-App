package inc.generics.profile_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_local.sp.SPHelper
import inc.generics.profile_data.models.ProfileInfo

class ProfileRepository(
    private val spHelper: SPHelper,
    private val api: DuetApi
) {
    suspend fun getProfileInformation(): ProfileInfo? {
        api.getMyProfilesInformation()
            .onSuccess {
                return it.toProfileInfoUi()
            }
        return null
    }

    fun logout() {
        spHelper.accessToken = null
        spHelper.refreshToken = null
    }
}
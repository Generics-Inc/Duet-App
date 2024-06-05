package inc.generics.profile_data

import inc.generics.duet_local.sp.SPHelper

class ProfileRepository(
    private val spHelper: SPHelper
) {
    fun logout() {
        spHelper.accessToken = null
        spHelper.refreshToken = null
    }
}
package inc.generics.duet.glue.data_sources.duet_api

import inc.generics.duet_api.util.TokenProvider
import inc.generics.duet_local.sp.SPHelper

class TokenProviderImpl(private val spHelper: SPHelper) : TokenProvider {
    override fun token(): String? = spHelper.accessToken
    override fun refToken(): String? = spHelper.refreshToken
}
package inc.generics.duet.glue.data_sources.duet_api

import inc.generics.duet_api.util.TokenProvider
import inc.generics.utils.helpers.SPHelper

class TokenProviderImpl(private val spHelper: SPHelper) : TokenProvider {
    override fun token(): String? = spHelper.accessToken
}
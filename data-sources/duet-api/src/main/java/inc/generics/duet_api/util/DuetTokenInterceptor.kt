package inc.generics.duet_api.util

import okhttp3.Interceptor
import okhttp3.Response

internal class DuetTokenInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuild = chain.request().newBuilder()
        val token = tokenProvider.token()

        token?.let {
            requestBuild.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuild.build())
    }
}

interface TokenProvider {
    fun token(): String?
}
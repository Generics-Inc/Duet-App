package inc.generics.duet_api.util

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

internal class DuetTokenInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuild = chain.request().newBuilder()
        val token = tokenProvider.token()

        if (chain.proceed(chain.request()).headers("REFRESH").isEmpty()) {
            token?.let {
                requestBuild.addHeader("Authorization", "Bearer $token")
            }
        } else {
            val requestBody = FormBody.Builder()
                .add("accessToken", token!!).build()
            requestBuild.addHeader("Authorization", "Bearer ${tokenProvider.refToken()}")
            return chain.proceed(requestBuild.post(requestBody).build())
        }

        return chain.proceed(requestBuild.build())
    }
}

interface TokenProvider {
    fun token(): String?

    fun refToken(): String?
}
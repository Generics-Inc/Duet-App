package inc.generics.duet_api
import inc.generics.duet_api.api.DuetApi
import inc.generics.duet_api.util.DuetTokenInterceptor
import inc.generics.duet_api.util.TokenProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun duetApi(
    baseUrl: String,
    tokenProvider: TokenProvider
): DuetApi {
    val okHttpClient = okHttpClient(tokenProvider)
    val retrofit = retrofit(okHttpClient, baseUrl)

    return retrofit.create(DuetApi::class.java)
}

internal fun retrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

internal fun okHttpClient(tokenProvider: TokenProvider): OkHttpClient {
    val interceptorLogging = HttpLoggingInterceptor()
    interceptorLogging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(DuetTokenInterceptor(tokenProvider))
        .addInterceptor(interceptorLogging)
        .build()
}
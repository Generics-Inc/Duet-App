package inc.generics.duet_api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
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
    val gson = gson()
    val retrofit = retrofit(okHttpClient, baseUrl, gson)

    return retrofit.create(DuetApi::class.java)
}

internal fun retrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String,
    gson: Gson
): Retrofit {
    return Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()
}

internal fun gson(): Gson {
    return GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()
}

internal fun okHttpClient(tokenProvider: TokenProvider): OkHttpClient {
    val interceptorLogging = HttpLoggingInterceptor()
    interceptorLogging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(DuetTokenInterceptor(tokenProvider))
        .addInterceptor(interceptorLogging)
        .build()
}
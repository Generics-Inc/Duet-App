package inc.generics.duet_api.api

import inc.generics.duet_api.models.auth.AuthResponseDto
import inc.generics.duet_api.models.auth.SignInDto
import inc.generics.duet_api.models.auth.VkSignInDto
import inc.generics.duet_api.models.profile.ProfileInfDto
import inc.generics.duet_api.models.profile.StatusUsersGroupDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *  @see <a href="https://duet.lorexiq.ru/swagger/#/">swagger api</a>
 * */
interface DuetApi {
    /* Authorization */
    @POST("api/auth/vkSignIn")
    suspend fun vkSignIn(@Body body: VkSignInDto): Result<AuthResponseDto>

    @POST("api/auth/signIn")
    suspend fun signIn(@Body body: SignInDto): Result<AuthResponseDto>

    @POST("api/auth/logout")
    suspend fun logout()

    @Headers("REFRESH:ON")
    @POST("api/auth/refresh")
    suspend fun refreshToken(): Result<AuthResponseDto>

    /* Profile */
    @GET("api/profiles/status")
    suspend fun getStatusUsersInGroup(): Result<StatusUsersGroupDto>

    @GET("api/profiles/me")
    suspend fun getMyProfilesInformation(): Result<ProfileInfDto>

    @GET("api/profiles/{id}")
    suspend fun getProfilesInformationById(@Path("id") id: Long): Result<ProfileInfDto>
}
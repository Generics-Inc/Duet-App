package inc.generics.duet_api.api

import inc.generics.duet_api.models.auth.AuthResponseDto
import inc.generics.duet_api.models.auth.SignInDto
import inc.generics.duet_api.models.auth.VkSignInDto
import inc.generics.duet_api.models.groups.Group
import inc.generics.duet_api.models.groups.GroupFull
import inc.generics.duet_api.models.groups.JoinToGroup
import inc.generics.duet_api.models.groups.RequestToGroup
import inc.generics.duet_api.models.profile.ProfileInfDto
import inc.generics.duet_api.models.profile.StatusUsersGroupDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.Date

/**
 *  @see <a href="https://duet.lorexiq.ru/swagger/#/">swagger api</a>
 * */
interface DuetApi {
    /* Authorization */
    @POST("auth/vkSignIn")
    suspend fun vkSignIn(@Body body: VkSignInDto): Result<AuthResponseDto>

    @POST("auth/signIn")
    suspend fun signIn(@Body body: SignInDto): Result<AuthResponseDto>

    @POST("auth/logout")
    suspend fun logout()

    @Headers("REFRESH:ON")
    @POST("auth/refresh")
    suspend fun refreshToken(): Result<AuthResponseDto>

    /* Profile */
    @GET("profiles/status")
    suspend fun getStatusUsersInGroup(): Result<StatusUsersGroupDto>

    @GET("profiles/me")
    suspend fun getMyProfilesInformation(): Result<ProfileInfDto>

    @GET("profiles/{id}")
    suspend fun getProfilesInformationById(@Path("id") id: Long): Result<ProfileInfDto>

    /* Groups */
    @GET("groups/me")
    suspend fun getUserGroup(): Result<Group>

    @GET("groups/me/full")
    suspend fun getUserGroupFull(): Result<GroupFull>

    @Multipart
    @POST("groups")
    suspend fun createGroup(
        @Part("name") name: String,
        @Part("relationStartedAt") relationStartedAt: Date,
        @Part("description") description: String,
        @Part file: MultipartBody.Part?
    ): Result<Group>

    @PATCH("groups/generateInviteCode")
    suspend fun generateNewInviteCode(): Result<Group>

    @PATCH("groups/join/{inviteCode}")
    suspend fun joinToGroup(@Path("inviteCode") inviteCode: String): Result<JoinToGroup>

    @DELETE("groups/kickPartner")
    suspend fun deletePartnerInGroup()

    @DELETE("groups/leave")
    suspend fun leaveGroup()

    /* Requests to Group */
    @GET("groups/requests")
    suspend fun requests(): Result<List<RequestToGroup>>

    @PATCH("groups/requests/accept/{id}")
    suspend fun acceptRequest(@Path("id") id: Long)

    @PATCH("groups/requests/cancel/{id}")
    suspend fun cancelRequest(@Path("id") id: Long)
}
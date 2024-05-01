package inc.generics.authorization.interactors

interface AuthorizationInteractor {
    suspend fun toAuthorizeVK(accessToken: String) : Boolean
}
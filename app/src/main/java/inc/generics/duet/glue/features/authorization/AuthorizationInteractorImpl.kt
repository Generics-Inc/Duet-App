package inc.generics.duet.glue.features.authorization

import inc.generics.authorization.interactors.AuthorizationInteractor
import kotlinx.coroutines.delay

class AuthorizationInteractorImpl : AuthorizationInteractor {
    override suspend fun toAuthorizeVK(accessToken: String): Boolean {
        delay(1000)
        return true //for test
        // call UseCase.execute() here
    }
}
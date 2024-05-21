package inc.generics.requests_data

import inc.generics.duet_api.api.DuetApi
import inc.generics.requests_data.models.Request
import inc.generics.utils_data.exceptions.safeRequest

class RequestsRepository(private val api: DuetApi) {
    suspend fun allRequest(): List<Request>? {
        return api.requests().getOrNull()?.map { requestDto ->
            requestDto.toRequest()
        }
    }

    suspend fun accept(requestId: Long): Boolean {
        return safeRequest {
            api.acceptRequest(requestId)
        }
    }

    suspend fun cancel(requestId: Long): Boolean {
        return safeRequest {
            api.cancelRequest(requestId)
        }
    }
}
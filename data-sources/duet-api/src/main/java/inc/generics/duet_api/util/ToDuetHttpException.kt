package inc.generics.duet_api.util

import retrofit2.HttpException

fun toDuetHttpException(throwable: Throwable): DuetHttpException {
    if (throwable is HttpException) {
        return when (throwable.code()) {
            400 -> DuetHttpException.BAD_REQUEST
            401 -> DuetHttpException.UNAUTHORIZED
            403 -> DuetHttpException.FORBIDDEN
            404 -> DuetHttpException.NOT_FOUND
            405 -> DuetHttpException.METHOD_NOT_ALLOWED
            409 -> DuetHttpException.CONFLICT
            500 -> DuetHttpException.INTERNAL_SERVER_ERROR
            502 -> DuetHttpException.BAD_GATEWAY
            503 -> DuetHttpException.SERVICE_UNAVAILABLE
            504 -> DuetHttpException.GATEWAY_TIMEOUT
            429 -> DuetHttpException.TOO_MANY_REQUESTS
            423 -> DuetHttpException.LOCKED
            else -> DuetHttpException.NOT_DEFINED
        }
   }
    return DuetHttpException.NOT_DEFINED
}

enum class DuetHttpException {
    BAD_REQUEST,                  // Некорректный запрос
    UNAUTHORIZED,                 // Отсутствие авторизации или неверные данные авторизации
    FORBIDDEN,                    // Отказано в доступе к ресурсу
    NOT_FOUND,                    // Ресурс не найден
    METHOD_NOT_ALLOWED,           // Метод не поддерживается для данного ресурса
    CONFLICT,                     // Конфликт в запросе (например, дубликат данных)
    INTERNAL_SERVER_ERROR,        // Внутренняя ошибка сервера
    BAD_GATEWAY,                  // Некорректный ответ от шлюза или прокси
    SERVICE_UNAVAILABLE,          // Сервис недоступен
    GATEWAY_TIMEOUT,              // Превышено время ожидания от шлюза или прокси
    TOO_MANY_REQUESTS,             // Превышено количество запросов
    LOCKED,                        // Нет достопа к материалам
    NOT_DEFINED
}


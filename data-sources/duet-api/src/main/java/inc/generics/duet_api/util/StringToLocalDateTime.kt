package inc.generics.duet_api.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

//todo: удалить так как используется .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") в Gson
internal fun stringToLocalDateTime(dateTime: String): LocalDateTime? =
    try {
        LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
    } catch (e: DateTimeParseException) {
        null
    }
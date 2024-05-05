package inc.generics.duet_api.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

internal fun stringToLocalDateTime(dateTime: String): LocalDateTime? =
    try {
        LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
    } catch (e: DateTimeParseException) {
        null
    }
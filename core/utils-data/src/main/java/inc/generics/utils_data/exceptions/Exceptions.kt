package inc.generics.utils_data.exceptions

suspend fun safeRequest(block: suspend () -> Unit): Boolean =
    try {
        block()
        true
    } catch (e: Exception) {
        false
    }
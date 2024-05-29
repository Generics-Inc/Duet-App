package inc.generics.presentation.utils

interface MultipleEventsCutter: EventsCutter {
    companion object
}

fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
    MultipleEventsCutterImpl()

internal class MultipleEventsCutterImpl : MultipleEventsCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= 400L) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}
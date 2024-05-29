package inc.generics.presentation.utils

interface EventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

fun EventsCutter.Companion.get(cutterType: CutterType): EventsCutter? = when(cutterType) {
    CutterType.MULTIPLE_EVENTS -> MultipleEventsCutter.get()
    CutterType.ONLY_ONE_CLICK -> OnlyOneClickCutter.get()
    CutterType.NONE -> null
}

enum class CutterType {
    MULTIPLE_EVENTS,
    ONLY_ONE_CLICK,
    NONE
}


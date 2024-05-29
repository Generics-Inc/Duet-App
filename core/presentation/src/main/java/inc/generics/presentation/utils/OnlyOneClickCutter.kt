package inc.generics.presentation.utils

interface OnlyOneClickCutter: EventsCutter {
    var isClickable: Boolean
    companion object
}

fun OnlyOneClickCutter.Companion.get(): OnlyOneClickCutter =
    OnlyOneClickEventsCutterImpl()

internal class OnlyOneClickEventsCutterImpl : OnlyOneClickCutter {
    override var isClickable: Boolean = true

    override fun processEvent(event: () -> Unit) {
        if (isClickable) {
            event.invoke()
            isClickable = false
        }
    }
}
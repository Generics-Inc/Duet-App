package inc.generics.duet.glue.presentation

import inc.generics.duet_local.sp.SPHelper
import inc.generics.presentation.utils.LanguageSaver

class LanguageSaverImpl(
    private val spHelper: SPHelper
) : LanguageSaver {
    override fun saveNewLanguage(languageId: Int) {
        spHelper.languageId = languageId
    }

    override fun getLanguageId(): Int = spHelper.languageId
}
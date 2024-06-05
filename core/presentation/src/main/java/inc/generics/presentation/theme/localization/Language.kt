package inc.generics.presentation.theme.localization

import inc.generics.presentation.R
import inc.generics.presentation.theme.localization.localized_strings.*

sealed class Language(
    val nameLanguage: String,
    val iconId: Int?,
    private val strings: HashMap<StringsKeys, String>
) {
    class Ru : Language(
        nameLanguage = "Русский",
        iconId = null,
        strings = russianStrings,
    )
    class En : Language(
        nameLanguage = "English",
        iconId = R.drawable.uk_flag,
        strings = englishStrings
    )

    operator fun get(key: StringsKeys): String = strings[key].toString()
}
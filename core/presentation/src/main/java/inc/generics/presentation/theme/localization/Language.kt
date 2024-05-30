package inc.generics.presentation.theme.localization

import inc.generics.presentation.theme.localization.localized_strings.*

sealed class Language(private val strings: HashMap<StringsKeys, String>) {
    class Ru : Language(russianStrings)
    class En : Language(englishStrings)

    operator fun get(key: StringsKeys): String = strings[key].toString()
}
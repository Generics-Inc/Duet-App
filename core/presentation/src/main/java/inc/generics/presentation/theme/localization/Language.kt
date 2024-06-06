package inc.generics.presentation.theme.localization

import inc.generics.presentation.R
import inc.generics.presentation.theme.localization.localized_strings.englishStrings
import inc.generics.presentation.theme.localization.localized_strings.russianStrings

sealed class Language(
    val id: Int,
    val nameLanguage: String,
    val iconId: Int?,
    private val strings: HashMap<StringsKeys, String>
) {
    class Ru : Language(
        id = 0,
        nameLanguage = "Русский",
        iconId = R.drawable.ru_flag,
        strings = russianStrings,
    )
    class En : Language(
        id = 1,
        nameLanguage = "English",
        iconId = R.drawable.uk_flag,
        strings = englishStrings
    )

    operator fun get(key: StringsKeys): String = strings[key].toString()

    override operator fun equals(other: Any?): Boolean {
        return other is Language && other.javaClass == this.javaClass
    }

    override fun hashCode(): Int {
        var result = nameLanguage.hashCode()
        result = 31 * result + id
        result = 31 * result + (iconId ?: 0)
        result = 31 * result + strings.hashCode()
        return result
    }
}

fun allLanguages(): List<Language> = listOf(
    Language.Ru(),
    Language.En()
)

fun findLanguageElseDefault(id: Int?): Language {
    if (id == null)
        return Language.En()
    for(el in allLanguages()) {
        if (id == el.id)
            return el
    }
    return Language.En()
}
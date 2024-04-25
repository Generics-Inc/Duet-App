package inc.generics.presentation.theme.localization

sealed class Language(private val strings: HashMap<String, String>) {
    class Ru() : Language(russianStrings)
    class En() : Language(englishStrings)

    fun getString(key: String) = strings[key].toString()
}
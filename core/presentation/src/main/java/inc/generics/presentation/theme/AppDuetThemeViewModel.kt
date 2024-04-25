package inc.generics.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import inc.generics.presentation.theme.localization.Language
import org.koin.androidx.compose.koinViewModel

class AppDuetThemeViewModel : ViewModel() {
    private val _localization: MutableLiveData<Language> = MutableLiveData(Language.En())
    val localization: LiveData<Language> = _localization

    fun setLanguage(newLanguage: Language) {
        _localization.value = newLanguage
    }
}

@Composable
fun appDuetThemeViewModel() : AppDuetThemeViewModel {
    val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
    return koinViewModel(viewModelStoreOwner = viewModelStoreOwner)
}
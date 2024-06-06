package inc.generics.duet.glue.presentation.di

import inc.generics.duet.glue.presentation.LanguageSaverImpl
import inc.generics.presentation.theme.AppDuetThemeViewModel
import inc.generics.presentation.utils.LanguageSaver
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::AppDuetThemeViewModel)
    singleOf(::LanguageSaverImpl) { bind<LanguageSaver>()}
}
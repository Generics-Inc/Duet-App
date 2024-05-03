package inc.generics.duet.glue.presentation.di

import inc.generics.presentation.theme.AppDuetThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::AppDuetThemeViewModel)
}
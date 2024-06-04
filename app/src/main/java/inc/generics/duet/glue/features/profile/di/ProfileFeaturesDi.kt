package inc.generics.duet.glue.features.profile.di

import inc.generics.profile.view_models.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val profileFeaturesModule = module {
    viewModelOf(::ProfileViewModel)
}
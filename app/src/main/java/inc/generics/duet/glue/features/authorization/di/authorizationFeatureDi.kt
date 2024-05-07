package inc.generics.duet.glue.features.authorization.di

import inc.generics.authorization.AuthorizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authorizationFeatureModule = module {
    viewModelOf(::AuthorizationViewModel)
}
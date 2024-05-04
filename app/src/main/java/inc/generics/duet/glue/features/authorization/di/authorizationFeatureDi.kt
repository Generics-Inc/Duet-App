package inc.generics.duet.glue.features.authorization.di

import inc.generics.authorization.AuthorizationViewModel
import inc.generics.authorization.interactors.AuthorizationInteractor
import inc.generics.duet.glue.features.authorization.AuthorizationInteractorImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authorizationFeatureModule = module {
    factoryOf(::AuthorizationInteractorImpl) { bind<AuthorizationInteractor>() }
    viewModelOf(::AuthorizationViewModel)
}
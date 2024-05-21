package inc.generics.duet.glue.features.requests.di

import inc.generics.requests.RequestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val requestsFeatureDI = module {
    viewModelOf(::RequestsViewModel)
}
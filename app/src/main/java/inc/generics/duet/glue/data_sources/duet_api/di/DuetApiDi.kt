package inc.generics.duet.glue.data_sources.duet_api.di

import inc.generics.duet.glue.data_sources.duet_api.TokenProviderImpl
import inc.generics.duet_api.duetApi
import inc.generics.duet_api.util.TokenProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val duetApiModule = module {
    singleOf(::TokenProviderImpl) { bind<TokenProvider>() }
    single { duetApi("https://duet.lorexiq.ru/api/", get()) }
}
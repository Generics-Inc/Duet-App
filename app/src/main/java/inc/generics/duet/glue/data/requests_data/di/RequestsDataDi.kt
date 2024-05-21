package inc.generics.duet.glue.data.requests_data.di

import inc.generics.requests_data.RequestsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val requestsDataModule = module {
    singleOf(::RequestsRepository)
}
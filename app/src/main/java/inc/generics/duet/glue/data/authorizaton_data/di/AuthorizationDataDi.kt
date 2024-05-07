package inc.generics.duet.glue.data.authorizaton_data.di

import inc.generics.authorization_data.AuthorizationRepository
import inc.generics.authorization_data.util.DeviceInfProvider
import inc.generics.duet.glue.data.authorizaton_data.DeviceInfProviderImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authorizationDataModule = module {
    factoryOf(::DeviceInfProviderImpl) { bind<DeviceInfProvider>()}
    singleOf(::AuthorizationRepository)
}
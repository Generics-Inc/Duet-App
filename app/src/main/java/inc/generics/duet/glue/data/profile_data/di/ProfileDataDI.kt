package inc.generics.duet.glue.data.profile_data.di

import inc.generics.profile_data.ProfileRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileDataModule = module {
    singleOf(::ProfileRepository)
}
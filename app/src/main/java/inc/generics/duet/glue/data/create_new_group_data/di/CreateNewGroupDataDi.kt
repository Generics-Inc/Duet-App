package inc.generics.duet.glue.data.create_new_group_data.di

import inc.generics.create_new_group.CreateNewGroupRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val createNewGroupDataModule = module {
    singleOf(::CreateNewGroupRepository)
}
package inc.generics.duet.glue.data.group_data.di

import inc.generics.group_data.GroupRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val groupDataModule = module {
    singleOf(::GroupRepository)
}
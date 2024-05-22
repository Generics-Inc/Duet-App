package inc.generics.duet.glue.data.join_to_group_data.di

import inc.generics.join_to_group_data.JoinToGroupRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val joinToGroupModule = module {
    singleOf(::JoinToGroupRepository)
}
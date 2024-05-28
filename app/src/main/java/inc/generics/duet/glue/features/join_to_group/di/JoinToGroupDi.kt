package inc.generics.duet.glue.features.join_to_group.di

import inc.generics.join_to_group.JoinToGroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val joinToGroupFeatureModule = module {
    viewModelOf(::JoinToGroupViewModel)
}
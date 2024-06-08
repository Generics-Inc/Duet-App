package inc.generics.duet.glue.features.create_new_group.di

import inc.generics.create_new_group.CreateNewGroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val createNewGroupFeatureModule = module {
    viewModelOf(::CreateNewGroupViewModel)
}
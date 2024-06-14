package inc.generics.duet.glue.features.group.di

import inc.generics.group.vm.GroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val groupFeaturesModule = module {
    viewModelOf(::GroupViewModel)
}
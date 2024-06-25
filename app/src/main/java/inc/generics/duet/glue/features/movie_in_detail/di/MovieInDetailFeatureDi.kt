package inc.generics.duet.glue.features.movie_in_detail.di

import inc.generics.movie_in_detail.vm.MovieInDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val movieInDetailFeatureModule = module {
    viewModelOf(::MovieInDetailViewModel)
}
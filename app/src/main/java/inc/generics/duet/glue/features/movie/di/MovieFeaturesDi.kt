package inc.generics.duet.glue.features.movie.di

import inc.generics.movie.vm.ActionItemBottomSheetViewModel
import inc.generics.movie.vm.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val movieFeaturesModule = module {
    viewModelOf(::MovieViewModel)
    viewModelOf(::ActionItemBottomSheetViewModel)
}
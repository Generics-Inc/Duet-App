package inc.generics.duet.glue.features.new_movie_hdrezka.di

import inc.generics.new_movie_hdrezka.vm.NewMovieHdRezkaViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val newMovieHdRezkaFeaturesModule = module {
    viewModelOf(::NewMovieHdRezkaViewModel)
}
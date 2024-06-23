package inc.generics.duet.glue.data.new_movie_hdrezka.di

import inc.generics.new_movie_hdrezka.NewMovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val newMovieHdRezkaDataModule = module {
    singleOf(::NewMovieRepository)
}
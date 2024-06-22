package inc.generics.duet.glue.data.movie_data.di

import inc.generics.movie_data.MovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val movieDataModule = module {
    singleOf(::MovieRepository)
}
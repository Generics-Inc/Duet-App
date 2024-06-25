package inc.generics.duet.glue.data.movie_in_detail_data.di

import inc.generics.movie_in_detail_data.MovieInDetailRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val movieInDetailDataModule = module {
    singleOf(::MovieInDetailRepository)
}
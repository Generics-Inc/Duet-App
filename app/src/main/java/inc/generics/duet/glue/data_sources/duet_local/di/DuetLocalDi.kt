package inc.generics.duet.glue.data_sources.duet_local.di

import inc.generics.duet_local.sp.SPHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val duetLocalModule = module {
    singleOf(::SPHelper)
}
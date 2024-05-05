package inc.generics.duet.glue.core.di

import inc.generics.utils.helpers.SPHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreUtilsModule = module {
    singleOf(::SPHelper)
}
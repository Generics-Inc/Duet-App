package inc.generics.duet.glue.android_utils.di

import inc.generics.android_utils.DeviceInfHelper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val androidUtilsModule = module {
    factoryOf(::DeviceInfHelper)
}
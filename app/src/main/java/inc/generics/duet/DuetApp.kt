package inc.generics.duet

import android.app.Application
import inc.generics.duet.glue.android_utils.di.androidUtilsModule
import inc.generics.duet.glue.data.authorizaton_data.di.authorizationDataModule
import inc.generics.duet.glue.data_sources.duet_api.di.duetApiModule
import inc.generics.duet.glue.data_sources.duet_local.di.duetLocalModule
import inc.generics.duet.glue.features.authorization.di.authorizationFeatureModule
import inc.generics.duet.glue.main.di.mainModule
import inc.generics.duet.glue.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DuetApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DuetApp)
            modules(
                mainModule,
                androidUtilsModule,
                authorizationDataModule,
                authorizationFeatureModule,
                presentationModule,
                duetLocalModule,
                duetApiModule
            )
        }
    }
}
package inc.generics.duet

import android.app.Application
import inc.generics.duet.glue.authorization.di.authorizationFeatureModule
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
                authorizationFeatureModule,
                presentationModule
            )
        }
    }
}
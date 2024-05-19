package inc.generics.duet

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import inc.generics.duet.glue.android_utils.di.androidUtilsModule
import inc.generics.duet.glue.data.authorizaton_data.di.authorizationDataModule
import inc.generics.duet.glue.data.create_new_group_data.di.createNewGroupDataModule
import inc.generics.duet.glue.data.group_without_partner_data.di.groupWithoutPartnerDataModule
import inc.generics.duet.glue.data_sources.duet_api.di.duetApiModule
import inc.generics.duet.glue.data_sources.duet_local.di.duetLocalModule
import inc.generics.duet.glue.features.authorization.di.authorizationFeatureModule
import inc.generics.duet.glue.features.create_new_group.di.CreateNewGroupFeatureModule
import inc.generics.duet.glue.features.group_without_partner.di.groupWithoutPartnerFeaturesModule
import inc.generics.duet.glue.main.di.mainModule
import inc.generics.duet.glue.presentation.di.presentationModule
import okhttp3.OkHttpClient
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DuetApp : Application(), ImageLoaderFactory {
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
                createNewGroupDataModule,
                CreateNewGroupFeatureModule,
                groupWithoutPartnerDataModule,
                groupWithoutPartnerFeaturesModule,
                presentationModule,
                duetLocalModule,
                duetApiModule
            )
        }
    }

    override fun newImageLoader(): ImageLoader =
        ImageLoader.Builder(applicationContext).okHttpClient(get<OkHttpClient>())
            .logger(DebugLogger()).build()
}
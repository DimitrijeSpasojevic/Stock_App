package rs.raf.jun.dimitrije_spasojevic_10820rn.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.jun.dimitrije_spasojevic_10820rn.modules.coreModule
import timber.log.Timber

class RafStockApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
    private fun initKoin() {
        val modules = listOf(
            coreModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@RafStockApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }
}
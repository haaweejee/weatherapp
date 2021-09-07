package id.haweje.weatherapp

import android.app.Application
import id.haweje.weatherapp.BuildConfig.DEBUG
import timber.log.Timber

open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
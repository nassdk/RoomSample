package com.test.roomsample

import android.app.Application
import com.test.roomsample.di.AppComponent
import com.test.roomsample.di.DaggerAppComponent
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import timber.log.Timber

class AppDelegate : Application(), AppProviderHolder {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initDi()
        initTimber()
    }

    private fun initDi() {
        (getAppProvider() as AppComponent).inject(this)
    }

    private fun initTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun getAppProvider(): AppProvider {
        if (!::appComponent.isInitialized) {
            appComponent = DaggerAppComponent
                .factory()
                .create(applicationContext)
        }

        return appComponent
    }
}
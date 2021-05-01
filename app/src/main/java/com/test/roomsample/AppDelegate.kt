package com.test.roomsample

import android.app.Application
import com.test.roomsample.di.AppComponent
import com.test.roomsample.di.DaggerAppComponent
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder

class AppDelegate : Application(), AppProviderHolder {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        (getAppProvider() as AppComponent).inject(this)
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
package com.test.roomsample.launcher.di

import android.app.Activity
import com.test.roomsample.launcher.AppActivity
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import com.test.roomsample.library.coreui.di.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = [AppProvider::class])
interface AppScreenComponent {

    fun inject(app: AppActivity)

    companion object {
        fun create(activity: Activity): AppScreenComponent {
            val appProvider = (activity.application as AppProviderHolder).getAppProvider()
            return DaggerAppScreenComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
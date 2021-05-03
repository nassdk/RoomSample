package com.test.roomsample.feature.live.presentation.di

import android.app.Activity
import com.test.roomsample.feature.live.presentation.LiveScoresFragment
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import com.test.roomsample.library.coreui.di.ScreenScope
import dagger.Component

@ScreenScope
@Component(
    modules = [LiveScoresModule::class],
    dependencies = [AppProvider::class]
)
interface LiveScoresComponent {
    fun inject(fragment: LiveScoresFragment)

    companion object {
        fun create(activity: Activity): LiveScoresComponent {
            val appProvider = (activity.application as AppProviderHolder).getAppProvider()
            return DaggerLiveScoresComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
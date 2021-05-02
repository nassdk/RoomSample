package com.test.roomsample.library.coreui.tab

import android.app.Activity
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import com.test.roomsample.library.coreui.di.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = [AppProvider::class])
interface TabComponent {
    fun inject(tab: TabFragment)

    @Component.Builder
    interface Builder {
        fun appProvider(appProvider: AppProvider): Builder

        fun build(): TabComponent
    }

    companion object {
        fun create(activity: Activity): TabComponent {
            val appProvider = (activity.application as AppProviderHolder).getAppProvider()
            return DaggerTabComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}

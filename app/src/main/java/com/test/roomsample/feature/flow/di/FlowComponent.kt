package com.test.roomsample.feature.flow.di

import android.app.Activity
import com.test.roomsample.feature.flow.DaggerFlowComponent
import com.test.roomsample.feature.flow.FlowActivity
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import com.test.roomsample.library.coreui.di.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = [AppProvider::class])
interface FlowComponent {
    fun inject(main: FlowActivity)

    @Component.Builder
    interface Builder {
        fun appProvider2(appProvider: AppProvider): Builder

        fun build(): FlowComponent
    }

    companion object {
        fun create(activity: Activity): FlowComponent {
            val appProvider = (activity.application as AppProviderHolder).getAppProvider()
            return DaggerFlowComponent.builder()
                .appProvider2(appProvider)
                .build()
        }
    }
}

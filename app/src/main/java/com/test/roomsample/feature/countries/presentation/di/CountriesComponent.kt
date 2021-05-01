package com.test.roomsample.feature.countries.presentation.di

import android.app.Activity
import com.test.roomsample.feature.countries.presentation.CountriesFragment
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import com.test.roomsample.library.coreui.di.ScreenScope
import dagger.Component

@ScreenScope
@Component(
    modules = [CountriesModule::class],
    dependencies = [AppProvider::class]
)
interface CountriesComponent {
    fun inject(fragment: CountriesFragment)

    companion object {
        fun create(activity: Activity): CountriesComponent {
            val appProvider = (activity.application as AppProviderHolder).getAppProvider()
            return DaggerCountriesComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
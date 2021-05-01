package com.test.roomsample.di

import android.content.Context
import com.test.roomsample.AppDelegate
import com.test.roomsample.feature.countries.data.di.CountriesDataModule
import com.test.roomsample.feature.countries.presentation.di.CountriesModule
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreimpl.common.di.CommonModule
import com.test.roomsample.library.coreimpl.navigation.NavigationModule
import com.test.roomsample.library.coreimpl.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        NavigationModule::class,
        CommonModule::class,
        CountriesDataModule::class
    ]
)
interface AppComponent : AppProvider {
    fun inject(app: AppDelegate)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
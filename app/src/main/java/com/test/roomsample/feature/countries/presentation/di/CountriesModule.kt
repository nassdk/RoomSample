package com.test.roomsample.feature.countries.presentation.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.test.roomsample.feature.countries.domain.usecase.LoadCountriesUseCase
import com.test.roomsample.feature.countries.presentation.CountriesStoreFactory
import com.test.roomsample.library.coreui.di.ScreenScope
import com.test.roomsample.library.coreui.error.UiErrorHandler
import dagger.Module
import dagger.Provides
import me.aartikov.alligator.Navigator

@Module
object CountriesModule {

    @Provides
    @ScreenScope
    fun provideCountriesStoreFactory(
        navigator: Navigator,
        loadCountriesUseCase: LoadCountriesUseCase,
        errorHandler: UiErrorHandler
    ) = CountriesStoreFactory(
        storeFactory = DefaultStoreFactory,
        navigator = navigator,
        loadCountriesUseCase = loadCountriesUseCase,
        errorHandler = errorHandler
    ).create()
}
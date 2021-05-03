package com.test.roomsample.feature.live.presentation.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.test.roomsample.feature.live.domain.usecase.LoadLiveScoresUseCase
import com.test.roomsample.feature.live.presentation.LiveScoresStoreFactory
import com.test.roomsample.library.coreui.di.ScreenScope
import com.test.roomsample.library.coreui.error.UiErrorHandler
import dagger.Module
import dagger.Provides

@Module
object LiveScoresModule {

    @Provides
    @ScreenScope
    fun provideLiveScoresStore(
        loadLiveScoresUseCase: LoadLiveScoresUseCase,
        errorHandler: UiErrorHandler
    ) = LiveScoresStoreFactory(
        storeFactory = DefaultStoreFactory,
        loadLiveScoresUseCase = loadLiveScoresUseCase,
        errorHandler = errorHandler
    ).create()
}
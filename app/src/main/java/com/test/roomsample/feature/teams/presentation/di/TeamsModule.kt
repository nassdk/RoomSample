package com.test.roomsample.feature.teams.presentation.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.test.roomsample.feature.teams.domain.usecase.LoadTeamsUseCase
import com.test.roomsample.feature.teams.presentation.TeamsStoreFactory
import com.test.roomsample.library.coreui.di.ScreenScope
import com.test.roomsample.library.coreui.error.UiErrorHandler
import dagger.Module
import dagger.Provides

@Module
object TeamsModule {

    @Provides
    @ScreenScope
    fun provideTeamsStore(
        loadTeamsUseCase: LoadTeamsUseCase,
        errorHandler: UiErrorHandler
    ) = TeamsStoreFactory(
        storeFactory = DefaultStoreFactory,
        loadTeamsUseCase = loadTeamsUseCase,
        errorHandler = errorHandler
    ).create()
}
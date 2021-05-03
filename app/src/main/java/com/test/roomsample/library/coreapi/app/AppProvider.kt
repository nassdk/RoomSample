package com.test.roomsample.library.coreapi.app

import android.content.Context
import com.test.roomsample.feature.countries.domain.CountriesUseCaseProvider
import com.test.roomsample.feature.live.domain.LiveScoresUseCaseProvider
import com.test.roomsample.library.coreapi.common.CommonProvider
import com.test.roomsample.library.coreapi.navigation.NavigationProvider
import com.test.roomsample.library.coreapi.network.NetworkProvider
import com.test.roomsample.library.coreui.di.UiProvider

interface AppProvider
    : NetworkProvider,
    UiProvider,
    NavigationProvider,
    CommonProvider,
    CountriesUseCaseProvider,
    LiveScoresUseCaseProvider {
    fun context(): Context
}
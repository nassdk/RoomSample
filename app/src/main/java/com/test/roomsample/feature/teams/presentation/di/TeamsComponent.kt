package com.test.roomsample.feature.teams.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.test.roomsample.feature.teams.presentation.TeamsFragment
import com.test.roomsample.library.coreapi.app.AppProvider
import com.test.roomsample.library.coreapi.app.AppProviderHolder
import com.test.roomsample.library.coreui.di.ScreenScope
import dagger.Component

@Component(
    modules = [TeamsModule::class],
    dependencies = [AppProvider::class]
)
@ScreenScope
interface TeamsComponent {
    fun inject(fragment: TeamsFragment)

    companion object {

        fun create(activity: AppCompatActivity): TeamsComponent {
            val appProvider = (activity.application as AppProviderHolder).getAppProvider()
            return DaggerTeamsComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
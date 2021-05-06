package com.test.roomsample.feature.teams.data.di

import com.test.roomsample.feature.teams.data.network.TeamsApi
import com.test.roomsample.feature.teams.data.repository.TeamsRepositoryImpl
import com.test.roomsample.feature.teams.domain.repository.TeamsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
abstract class TeamsDataModule {

    @Binds
    @Singleton
    abstract fun bindTeamsRepository(repositoryImpl: TeamsRepositoryImpl): TeamsRepository

    companion object {

        @Provides
        @Singleton
        fun provideTeamsApi(retrofit: Retrofit): TeamsApi = retrofit.create()
    }
}
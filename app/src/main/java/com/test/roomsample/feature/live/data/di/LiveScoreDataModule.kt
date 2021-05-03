package com.test.roomsample.feature.live.data.di

import com.test.roomsample.feature.live.data.network.LiveScoresApi
import com.test.roomsample.feature.live.data.repository.LiveScoresRepositoryImpl
import com.test.roomsample.feature.live.domain.repository.LiveScoresRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
abstract class LiveScoreDataModule {

    @Binds
    @Singleton
    abstract fun provideLiveScoresRepository(repositoryImpl: LiveScoresRepositoryImpl): LiveScoresRepository

    companion object {

        @Provides
        @Singleton
        fun provideLiveScoreApi(retrofit: Retrofit): LiveScoresApi = retrofit.create()
    }
}
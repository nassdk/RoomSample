package com.test.roomsample.feature.countries.data.di

import com.test.roomsample.feature.countries.data.database.CountriesDao
import com.test.roomsample.feature.countries.data.network.CountriesApi
import com.test.roomsample.feature.countries.data.repository.CountriesRepositoryImpl
import com.test.roomsample.feature.countries.domain.repository.CountriesRepository
import com.test.roomsample.library.coreimpl.database.AppDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
abstract class CountriesDataModule {

    @Binds
    @Singleton
    abstract fun bindCountriesRepository(repositoryImpl: CountriesRepositoryImpl): CountriesRepository

    companion object {

        @Provides
        @Singleton
        fun provideCountriesDao(roomDatabase: AppDataBase): CountriesDao =
            roomDatabase.countriesDao()

        @Provides
        @Singleton
        fun provideCountriesApi(retrofit: Retrofit): CountriesApi = retrofit.create()
    }
}
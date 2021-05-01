package com.test.roomsample.di

import com.test.roomsample.library.coreapi.app.AppInitializer
import com.test.roomsample.library.coreimpl.app.TimberInitializer
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindTimber(timberInitializer: TimberInitializer): AppInitializer
}
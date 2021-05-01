package com.test.roomsample.library.coreimpl.common.di

import com.test.roomsample.library.coreapi.common.error.ErrorHandler
import com.test.roomsample.library.coreapi.common.resourcemanager.ResourceManager
import com.test.roomsample.library.coreimpl.common.error.ErrorHandlerImpl
import com.test.roomsample.library.coreimpl.common.resourcemanager.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CommonModule {

    @Binds
    @Singleton
    abstract fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler

    @Binds
    @Singleton
    abstract fun bindResourceManager(impl: ResourceManagerImpl): ResourceManager
}
package com.test.roomsample.library.coreapi.common

import com.test.roomsample.library.coreapi.common.error.ErrorHandler
import com.test.roomsample.library.coreapi.common.resourcemanager.ResourceManager

interface CommonProvider {
    fun resourceManager(): ResourceManager
    fun errorHandler(): ErrorHandler
}
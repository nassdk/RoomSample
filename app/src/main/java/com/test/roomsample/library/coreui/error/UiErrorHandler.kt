package com.test.roomsample.library.coreui.error

import com.test.roomsample.R
import com.test.roomsample.library.coreapi.common.error.ErrorHandler
import com.test.roomsample.library.coreapi.common.resourcemanager.ResourceManager
import com.test.roomsample.library.coreimpl.common.error.ErrorWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiErrorHandler @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val resourceManager: ResourceManager
) {

    fun proceedError(throwable: Throwable, errorListener: (String) -> Unit = {}) {

        when (val error = errorHandler.getError(throwable = throwable)) {
            ErrorWrapper.ServerError -> errorListener(resourceManager.getString(R.string.error_server_internal))
            ErrorWrapper.Timeout -> errorListener(resourceManager.getString(R.string.error_server_timeout))
            ErrorWrapper.Network -> errorListener(resourceManager.getString(R.string.error_server_network))
            ErrorWrapper.Unknown -> errorListener(resourceManager.getString(R.string.error_server_unknown))
            is ErrorWrapper.CustomError -> errorListener(error.message)
        }
    }
}
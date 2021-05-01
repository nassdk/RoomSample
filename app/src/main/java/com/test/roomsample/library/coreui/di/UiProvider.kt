package com.test.roomsample.library.coreui.di

import com.test.roomsample.library.coreui.error.UiErrorHandler

interface UiProvider {
    fun uiErrorHandler(): UiErrorHandler
}
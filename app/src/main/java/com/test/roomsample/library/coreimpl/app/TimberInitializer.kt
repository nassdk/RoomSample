package com.test.roomsample.library.coreimpl.app

import android.content.Context
import com.test.roomsample.BuildConfig
import com.test.roomsample.library.coreapi.app.AppInitializer
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor() : AppInitializer {

    override fun init(context: Context) {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
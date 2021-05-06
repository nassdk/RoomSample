package com.test.roomsample.library.coreimpl.database.di

import android.content.Context
import androidx.room.Room
import com.test.roomsample.library.coreimpl.database.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "RoomSampleDataBase"
    ).build()
}
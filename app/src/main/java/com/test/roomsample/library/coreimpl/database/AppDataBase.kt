package com.test.roomsample.library.coreimpl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.roomsample.feature.countries.data.database.CountriesDao
import com.test.roomsample.feature.countries.data.database.CountryEntity

@Database(
    entities = [
        CountryEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}
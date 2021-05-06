package com.test.roomsample.feature.countries.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountriesDao {

    @Query(value = "SELECT * FROM ${CountryEntity.TABLE_NAME}")
    suspend fun fetchCountries(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun saveCountries(countries: List<CountryEntity>)
}
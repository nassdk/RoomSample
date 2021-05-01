package com.test.roomsample.feature.countries.domain.repository

import com.test.roomsample.feature.countries.domain.model.CountryModel

interface CountriesRepository {
    suspend fun getCountries(): List<CountryModel>
}
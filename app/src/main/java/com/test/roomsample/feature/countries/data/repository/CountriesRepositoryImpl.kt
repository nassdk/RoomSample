package com.test.roomsample.feature.countries.data.repository

import com.test.roomsample.feature.countries.data.network.CountriesApi
import com.test.roomsample.feature.countries.data.network.CountriesMapper
import com.test.roomsample.feature.countries.domain.model.CountryModel
import com.test.roomsample.feature.countries.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val api: CountriesApi,
    private val mapper: CountriesMapper
) : CountriesRepository {

    override suspend fun getCountries(): List<CountryModel> =
        mapper.map(model = api.loadCountriesModel())
}
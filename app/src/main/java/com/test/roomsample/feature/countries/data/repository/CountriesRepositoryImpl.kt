package com.test.roomsample.feature.countries.data.repository

import com.test.roomsample.feature.countries.data.database.CountriesDao
import com.test.roomsample.feature.countries.data.network.CountriesApi
import com.test.roomsample.feature.countries.data.network.CountriesMapper
import com.test.roomsample.feature.countries.domain.repository.CountriesRepository
import com.test.roomsample.library.coreimpl.network.connection.NetworkChecking
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val api: CountriesApi,
    private val mapper: CountriesMapper,
    private val networkChecking: NetworkChecking,
    private val countriesDao: CountriesDao
) : CountriesRepository {

    override suspend fun getCountries() =
        if (networkChecking.hasConnection())
            mapper.map(model = api.loadCountriesModel())
                .also {
                    countriesDao.saveCountries(countries = mapper.mapToEntity(it))
                }
        else mapper.mapFromEntity(entities = countriesDao.fetchCountries())
}
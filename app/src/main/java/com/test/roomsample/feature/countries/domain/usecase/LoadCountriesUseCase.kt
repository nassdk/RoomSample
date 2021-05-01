package com.test.roomsample.feature.countries.domain.usecase

import com.test.roomsample.feature.countries.domain.repository.CountriesRepository
import javax.inject.Inject

class LoadCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
) {
    suspend fun invoke() = repository.getCountries()
}
package com.test.roomsample.feature.countries.domain

import com.test.roomsample.feature.countries.domain.usecase.LoadCountriesUseCase

interface CountriesUseCaseProvider {
    fun getCountriesUseCase(): LoadCountriesUseCase
}
package com.test.roomsample.feature.countries.data.network

import com.test.roomsample.feature.countries.domain.model.CountryModel
import javax.inject.Inject

class CountriesMapper @Inject constructor() {

    fun map(model: CountriesResponseNet) = model.data.countries.map { country ->
        country.run {
            CountryModel(
                id = id,
                name = name
            )
        }
    }
}
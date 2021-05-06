package com.test.roomsample.feature.countries.data.network

import com.test.roomsample.feature.countries.data.database.CountryEntity
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


    fun mapFromEntity(entities: List<CountryEntity>) = entities.map { entity ->
        entity.run {
            CountryModel(
                id = id,
                name = name
            )
        }
    }


    fun mapToEntity(countries: List<CountryModel>) = countries.map { country ->
        country.run {
            CountryEntity(
                id = id,
                name = name
            )
        }
    }

}